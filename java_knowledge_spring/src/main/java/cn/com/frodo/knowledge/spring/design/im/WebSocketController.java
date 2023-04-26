package cn.com.frodo.knowledge.spring.design.im;


import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/websocket")
public class WebSocketController {

    @Resource
    private WebSocketService webSocketService;

    @RequestMapping(value = "/socketSend", method = RequestMethod.POST)
    @ResponseBody
    public String socketSend(@RequestBody ForwardSocketUserInfoRequest request) {
        return webSocketService.socketSend(request);
    }

    /**
     * 该接口供本服务内部通过转发到对用的服务器发送http调用
     */
    @RequestMapping(value = "/forwardSend", method = RequestMethod.POST)
    @ResponseBody
    public  String forwardSend(@RequestBody ForwardSocketUserInfoRequest request) {
        return webSocketService.forwardSend(request);
    }
}
