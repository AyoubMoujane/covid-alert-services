package covidlocation.services;

import covidlocation.models.Location;
import covidlocation.models.PositiveUser;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private static final String TOPIC = "PositiveUsersTopic";

    @KafkaListener(topics = TOPIC, groupId = "group_id")
    public void consume(Object positiveUser)
    {
        System.out.println("Positive user message consumed -> "+ positiveUser);
        try {
            PositiveUser positiveUser1 = (PositiveUser) positiveUser;
            System.out.println("Casted received message" + positiveUser1);

        } catch(Exception e) {
            System.out.println(e);
        }
    }

}
