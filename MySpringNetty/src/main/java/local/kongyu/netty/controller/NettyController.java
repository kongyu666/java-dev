package local.kongyu.netty.controller;

import local.kongyu.netty.netty.MyNettyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NettyController {
    @Autowired
    private MyNettyClient myNettyClient;

    @GetMapping("/send")
    public String sendMessageToNetty(@RequestParam String message) {
        return myNettyClient.sendMessage(message);
    }
}

