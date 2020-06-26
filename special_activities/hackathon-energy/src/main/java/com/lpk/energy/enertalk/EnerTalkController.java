package com.lpk.energy.enertalk;

import com.sun.xml.internal.ws.resources.ClientMessages;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Minwoo on 2017. 4. 8..
 */

@RestController
public class EnerTalkController {

    @MessageMapping("/hello")
    @SendTo("/topic/roomId")
    public int broadcasting(ClientMessages messages) throws Exception {

        System.out.println("test");

        return 100;
    }
}
