package kafkapackage.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kafkapackage.models.Location;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private static final String TOPIC = "test_topic";
    @Autowired
    private KafkaTemplate<String, Object> kafkaLocationTemplate;

    /*@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;




    public void sendMessage(String message){
        this.kafkaTemplate.send(TOPIC,message);
    }

     */

    public void sendLocation(Location location){
        this.kafkaLocationTemplate.send("location_topic",location);
    }

    @Bean
    public NewTopic createTopic(){
        return new NewTopic(TOPIC,3,(short) 1);
    }


    public void saveCreateLocationLog(Location location) {
        System.out.println("Location created -> "+ location);
        this.sendLocation(location);
    }

    public void sendLocationMessage(Location location){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(location);
        this.kafkaLocationTemplate.send("location_topic",json);
    }
}