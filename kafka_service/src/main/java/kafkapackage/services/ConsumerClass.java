package kafkapackage.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class ConsumerClass {
    private static final Logger logger = LoggerFactory.getLogger((ConsumerClass.class));


    @KafkaListener(topics = "user")
    public void consume(String message) {
        logger.info(String.format("**************** Consumer receiving message %s",message));


    }

}
