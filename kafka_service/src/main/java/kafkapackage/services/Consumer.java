package kafkapackage.services;

import kafkapackage.models.Location;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Consumer {

    /*
    @KafkaListener(topics = "test_topic",groupId = "group_id")
    public void consumeMessage(String message){

        System.out.println(message);
    }

     */

    private List<Location> locations = new ArrayList<>();


    @KafkaListener(topics = "location_topic",
            groupId = "group_id")
    public void consume(Location location, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition, @Header(KafkaHeaders.OFFSET) int offset)
    {
        System.out.println("Location consumed -> "+ location);
        System.out.println("From partition : "+ partition);
    }

    @KafkaListener(topicPartitions =
            {@TopicPartition(topic = "location_topic",
                    partitionOffsets = @PartitionOffset(partition="0", initialOffset = "0"))
            })
    public void consumeAllLocation(Location location)
    {
        locations.add(location);
        System.out.println(locations);

    }

}