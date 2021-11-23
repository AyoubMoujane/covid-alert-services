package covidtest.services;

import covidtest.models.PositiveUser;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private static final String TOPIC = "PositiveUsersTopic";
    @Autowired
    private KafkaTemplate<String, Object> kafkaLocationTemplate;

    public void sendPositiveUserMessage(PositiveUser positiveUser){
        this.kafkaLocationTemplate.send(TOPIC,positiveUser);
    }

    @Bean
    public NewTopic createTopic(){
        return new NewTopic(TOPIC,3,(short) 1);
    }


    public void saveCreateLocationLog(PositiveUser positiveUser) {
        System.out.println("Positive user message created -> "+ positiveUser);
        this.sendPositiveUserMessage(positiveUser);
    }
}
