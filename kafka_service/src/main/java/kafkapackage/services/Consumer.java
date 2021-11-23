package kafkapackage.services;

import kafkapackage.models.Location;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    /*
    @KafkaListener(topics = "test_topic",groupId = "group_id")
    public void consumeMessage(String message){

        System.out.println(message);
    }

     */

    @KafkaListener(topics = "location_topic",
            groupId = "group_id")
    public void consume(Location location)
    {
        System.out.println("Location consumed -> "+ location);
//        System.out.println(process().);
    }

//    @Bean
//    public java.util.function.Consumer<KStream<String, String>> process() {
//
//        return input ->
//                input.foreach((key, value) -> {
//                    System.out.println("Key: " + key + " Value: " + value);
//                });
//    }
}