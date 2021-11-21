package kafkapackage.controllers;

import kafkapackage.models.Location;
import kafkapackage.services.Producer;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("kafka")
public class KafkaController {

    private final Producer producer;

    @Autowired
    public KafkaController(Producer producer) {
        this.producer = producer;
    }
    /*
    @PostMapping("/publish")
    public void messageToTopic(@RequestParam("message") String message){
        this.producer.sendMessage(message);

    }

     */


    @PostMapping
    @RequestMapping("/locations")
    public void sendMessageToKafkaTopic(@RequestBody final Location location) {
        System.out.println(location);
        try {
            this.producer.saveCreateLocationLog(location);
        }
        catch (Exception exception) {
            System.out.println("Error in getNearUser");
            System.out.println(exception.getMessage());
            throw exception;

        }
    }


}
