package cn.com.frodo.knowledge.spring.design.im;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;

@Slf4j
@Service
public class WebSocketServiceImpl implements WebSocketService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RestTemplate restTemplate;

    @Value("${spring.websocket.port}")
    private String servicePort;

    /**
     * 根据redis中存入的websocket客户端接入信息，转发到对应的服务器做消息推送
     */
    @Override
    public String socketSend(ForwardSocketUserInfoRequest request) {
        String redisKey = WebSocketServer.websocketRedisKeyPrefix + request.getUserId();
        Object redisSocketObj = stringRedisTemplate.opsForValue().get(redisKey);
        SocketUserInfoDTO socketUserInfoDTO = null;
        if (null != redisSocketObj) {
            String resultStr = redisSocketObj.toString();
            socketUserInfoDTO = JSONObject.parseObject(resultStr, SocketUserInfoDTO.class);
        }
        if (null == socketUserInfoDTO) {
            log.error("给用户推送websocket消息失败");
            return "error";
        }
        Map<String, Map<String, SocketUserInfoSessionDTO>> mapMap = socketUserInfoDTO.getListMap();
        Map<String, SocketUserInfoSessionDTO> sessionDTOMap = mapMap.get(request.getUserId());
        //遍历map,根据Ip通过HTTP方式，传入sessionId参数，调用推送信息接口（这里的接口需要根据sessionId从内存中的map拿到对应的websocket,然后发送消息），推送信息到客户端
        Iterator<Map.Entry<String, SocketUserInfoSessionDTO>> entryIterator = sessionDTOMap.entrySet().iterator();
        try {
        while (entryIterator.hasNext()) {
            Map.Entry<String, SocketUserInfoSessionDTO> entry = entryIterator.next();
            String ipAndPort = InetAddress.getLocalHost().getHostAddress() + ":" + servicePort;
            if (ipAndPort.equals(entry.getValue().getIp())) {  //如果是本台服务器，直接发送
                try {
                    WebSocketServer.sendInfo(JSON.toJSONString(request.getObject()), request.getUserId(), entry.getValue().getSessionId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                request.setSessionId(entry.getValue().getSessionId());
                //通过HTTP转发到对应的服务器做处理
                restTemplate.postForEntity("http://" + entry.getValue().getIp() + "/websocket/forwardSend", request, String.class);
            }
        }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 该接口供本服务内部通过转发到对用的服务器发送http调用
     */
    @Override
    public String forwardSend(ForwardSocketUserInfoRequest request) {
        try {
            WebSocketServer.sendInfo(JSON.toJSONString(request.getObject()), request.getUserId(), request.getSessionId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
