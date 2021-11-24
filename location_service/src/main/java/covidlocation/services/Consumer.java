package covidlocation.services;

import covidlocation.models.Location;
import covidlocation.models.PositiveUser;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class Consumer {

    private static final String TOPIC = "PositiveUsersTopic";

    @KafkaListener(topics = TOPIC, groupId = "group_id")
    public void consume(String json)
    {
        Gson gson = new GsonBuilder().create();
        System.out.println("Positive user message consumed -> String "+ json);
        try {
            PositiveUser positiveUser1 = gson.fromJson(json, PositiveUser.class);
            System.out.println("Casted received message" + positiveUser1.getUser_id());

        } catch(Exception e) {
            System.out.println(e);
        }
    }

}
