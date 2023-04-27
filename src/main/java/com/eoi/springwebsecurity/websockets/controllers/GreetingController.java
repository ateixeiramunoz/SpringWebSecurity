package com.eoi.springwebsecurity.websockets.controllers;

import com.eoi.springwebsecurity.websockets.messages.Greeting;
import com.eoi.springwebsecurity.websockets.messages.HelloMessage;
import com.eoi.springwebsecurity.websockets.messages.PrivateMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;


/**
 * The type Greeting controller.
 */
@Controller
@Log4j2
public class GreetingController {

    /**
     * The Simp messaging template.
     */
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;


    /**
     * Greeting greeting.
     *
     * @param message the message
     * @return the greeting
     * @throws Exception the exception
     */
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {

        Thread.sleep(2000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");

    }

    /**
     * Send to specific user.
     *
     * @param message the message
     */
    @MessageMapping("/private")
    public void sendToSpecificUser(@Payload PrivateMessage message) {

        log.info("Recibida petición de mensaje privado");
        simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/specific", message);

    }



}
