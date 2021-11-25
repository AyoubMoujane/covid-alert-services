package com.example.notificationservice.controller;

import com.example.notificationservice.model.Message;
import com.example.notificationservice.model.ResponseMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class NotificationController {

   @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(final Message message) throws InterruptedException {
       System.out.println("ok");
       Thread.sleep(1000);
       return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
   }

}