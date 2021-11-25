package com.example.notificationservice.services;

import com.example.notificationservice.model.UserAlert;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private static final String TOPIC = "UserAlertTopic";

    @KafkaListener(topics = TOPIC, groupId = "group_id")
    public void consume(String json)
    {
        Gson gson = new GsonBuilder().create();
        System.out.println("UserAlert user message consumed -> String "+ json);
        try {
            UserAlert userAlert = gson.fromJson(json, UserAlert.class);
            System.out.println("Casted received message" + userAlert.getUser_id());

        } catch(Exception e) {
            System.out.println(e);
        }
    }

}
