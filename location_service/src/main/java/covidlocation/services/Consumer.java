package covidlocation.services;

import covidlocation.models.Location;
import covidlocation.models.PositiveUser;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private static final String TOPIC = "PositiveUsersTopic";

    @KafkaListener(topics = TOPIC, groupId = "group_id")
    public void consume(PositiveUser positiveUser)
    {
        System.out.println("Positive user message consumed -> "+ positiveUser);
    }

}
