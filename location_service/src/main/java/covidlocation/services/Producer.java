package covidlocation.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import covidlocation.models.UserAtRisk;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Producer {
    private static final String TOPIC = "UserAtRiskTopic";
    @Autowired
    private KafkaTemplate<String, Object> kafkaLocationTemplate;

    public void sendUserAtRiskMessage(List<UserAtRisk> listUserAtRisks){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(listUserAtRisks);
        System.out.println("send message : "+ json);
        this.kafkaLocationTemplate.send(TOPIC,json);
//        for (UserAtRisk userAtRisk:listUserAtRisks) {
//            String json = gson.toJson(userAtRisk);
//            System.out.println("send message : "+ json);
//            this.kafkaLocationTemplate.send(TOPIC,json);
//        }

    }

    @Bean
    public NewTopic createTopic(){
        return new NewTopic(TOPIC,3,(short) 1);
    }



}
