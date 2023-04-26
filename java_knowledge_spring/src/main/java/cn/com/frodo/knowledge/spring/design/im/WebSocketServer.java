package cn.com.frodo.knowledge.spring.design.im;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * https://www.yangzeng.net/article/4290185283/
 */
@Component
@ServerEndpoint("/websocket/{userId}")
@Slf4j
public class WebSocketServer {
    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     * <p>
     * Map的key为userId,List用于存放一个userId的多个终端，比如同一个userId,同时在手机端和PC登陆
     */
    private static ConcurrentHashMap<String, List<WebSocketServer>> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收userId
     */
    private String userId = "";

    /**
     * 自己封装的redis的工具类，用于将数据存放于redis,解决分布式下多服务器共享session
     */
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${spring.websocket.port}")
    private String servicePort;

    /**
     * 存储websocket客户端接入的信息key前缀
     */
    public static final String websocketRedisKeyPrefix = "WebSocket_";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        List<WebSocketServer> servers;
        List<WebSocketServer> webSocketServers = new ArrayList<>();
        //将接入的客户端信息添加到内存
        if (webSocketMap.containsKey(userId)) {
            //查询当前userId以及当前的session是否已经存在，如果存在，先移除再新增，如果不存在，直接新增
            webSocketServers = webSocketMap.get(userId).stream().filter(o -> o.session.getId().equals(session.getId())).collect(Collectors.toList());
        }
        if (webSocketMap.containsKey(userId) && webSocketServers.size() > 0) {
            webSocketServers = webSocketMap.get(userId);
            webSocketServers.removeIf(webSocketServer -> webSocketServer.session.getId().equals(session.getId()));
            servers = webSocketServers;
            servers.add(this);
            webSocketMap.put(userId, servers);
        } else {
            servers = null == webSocketMap.get(userId) ? new ArrayList<>() : webSocketMap.get(userId);
            servers.add(this);
            webSocketMap.put(userId, servers);
            addOnlineCount();//在线数加1
        }
        log.info("用户【" + userId + "】sessionId:[" + session.getId() + "]连接成功" + ",当前在线人数为:" + getOnlineCount());
        //region 将客户端连接信息存入redis
        try {
            /**
             * SocketUserInfoDTO
             *
             * 存储在redis中按照如下结构
             *                         ｜-- Ip
             *           -- sessionId1 ｜-- 其他信息
             *           --
             * userId -- -- sessionId2 ｜- ...
             *           --
             *           -- sessionId3 ｜-- Ip
             *           --            ｜-- 其他信息
             */
            SocketUserInfoDTO suid = null;
            SocketUserInfoSessionDTO socketUserInfoSessionDTO = new SocketUserInfoSessionDTO();
            socketUserInfoSessionDTO.setSessionId(session.getId());
            socketUserInfoSessionDTO.setUserId(userId);
            socketUserInfoSessionDTO.setIp(InetAddress.getLocalHost().getHostAddress() + ":" + servicePort);
            //需要从redis拉最新的客户端连接信息
            Object object = stringRedisTemplate.opsForValue().get(getSocketRedisKey(userId));
            if (null != object) {
                suid = JSONObject.parseObject(object.toString(), SocketUserInfoDTO.class);
            }
            SocketUserInfoDTO socketUserInfoDTO = new SocketUserInfoDTO();
            if (null == suid) {  //当前user没有保存的socket信息
                Map<String, Map<String, SocketUserInfoSessionDTO>> listMap = new HashMap<>();
                Map<String, SocketUserInfoSessionDTO> map = new HashMap<>();
                map.put(session.getId(), socketUserInfoSessionDTO);
                listMap.put(userId, map);
                socketUserInfoDTO.setListMap(listMap);
                //保存到redis
                stringRedisTemplate.opsForValue().set(getSocketRedisKey(userId), JSONObject.toJSONString(socketUserInfoDTO));
            } else { //当前user有保存的socket信息
                Map<String, Map<String, SocketUserInfoSessionDTO>> map = suid.getListMap();
                Map<String, SocketUserInfoSessionDTO> sessionDTOMap = map.get(userId);
                sessionDTOMap.put(session.getId(), socketUserInfoSessionDTO);
                map.put(userId, sessionDTOMap);
                socketUserInfoDTO.setListMap(map);
                stringRedisTemplate.opsForValue().set(getSocketRedisKey(userId), JSONObject.toJSONString(socketUserInfoDTO));
            }
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("用户:" + userId + ",网络异常!!!!!!");
        }
        //endregion
    }


    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        List<WebSocketServer> webSocketServers = new ArrayList<>();
        if (webSocketMap.containsKey(userId)) {
            webSocketServers = webSocketMap.get(userId).stream().filter(o -> o.session.getId().equals(session.getId())).collect(Collectors.toList());
        }
        if (webSocketMap.containsKey(userId) && webSocketServers.size() > 0) {
            webSocketServers = webSocketMap.get(userId);
            Iterator<WebSocketServer> iterator = webSocketServers.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().session.getId().equals(session.getId())) {
                    iterator.remove();
                }
            }
            webSocketMap.put(userId, webSocketServers);
            subOnlineCount();
            log.info("用户【" + userId + "】sessionId:[" + session.getId() + "]断开连接,当前在线人数为:" + getOnlineCount());
        }
        //从redis中移除对应的客户端
        Object redisSocketObj = stringRedisTemplate.opsForValue().get(getSocketRedisKey(userId));
        SocketUserInfoDTO suid = null;
        if (null != redisSocketObj) {
            String resultStr = redisSocketObj.toString();
            suid = JSONObject.parseObject(resultStr, SocketUserInfoDTO.class);
        }
        if (null != suid) {
            Map<String, Map<String, SocketUserInfoSessionDTO>> map = suid.getListMap();
            Map<String, SocketUserInfoSessionDTO> sessionDTOMap = map.get(userId);
            Iterator<Map.Entry<String, SocketUserInfoSessionDTO>> entryIterator = sessionDTOMap.entrySet().iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<String, SocketUserInfoSessionDTO> entry = entryIterator.next();
                if (session.getId().equals(entry.getValue().getSessionId())) {
                    entryIterator.remove();
                }
            }
            if (sessionDTOMap.size() <= 0) {
                map.remove(userId);
            }
            SocketUserInfoDTO socketUserInfoDTO = new SocketUserInfoDTO();
            if (map.size() <= 0) {
                stringRedisTemplate.delete(getSocketRedisKey(userId));
            } else {
                socketUserInfoDTO.setListMap(map);
                stringRedisTemplate.opsForValue().set(getSocketRedisKey(userId), JSONObject.toJSONString(socketUserInfoDTO));
            }
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("用户【" + userId + "】sessionId:[" + session.getId() + "]发送消息给服务端报文:" + message);
//        log.info("session" + session.getBasicRemote() + "|" + session.getId());
        try {
            sendMessage("服务端消息：" + "用户" + userId + "收到客户端的消息");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //可以群发消息
        //消息保存到数据库、redis
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.userId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送自定义消息
     */
    public static void sendInfo(String message, @PathParam("userId") String userId, String sessionId) throws IOException {
        log.info("发送消息到用户【" + userId + "】sessionId:[" + sessionId + "]发送消息给客户端报文:" + message);
        log.info(JSON.toJSONString("当前客户" + userId + "的所有客户端：" + webSocketMap.get(userId)));
        if (StringUtils.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
            for (WebSocketServer webSocketServer : webSocketMap.get(userId)) {
                //1、如果不考虑websocket有多台服务器的情况下，可以不用判断，推送消息的时候对该用户的所有终端都推送
                //2、当然如果业务需求不需要多终端推送，哪个终端有消息，就推送哪个，这里就不需要修改
                if (sessionId.equals(webSocketServer.session.getId())) {
                    webSocketServer.sendMessage(message);
                }
            }
        } else {
            log.error("用户" + userId + ",不在线！");
        }
    }

    /**
     * 构造redis的key值
     */
    private String getSocketRedisKey(String userId) {
        return websocketRedisKeyPrefix + userId;
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
