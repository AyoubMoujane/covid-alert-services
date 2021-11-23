package kafkapackage;

import kafkapackage.models.Location;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configurable
public class LocationKafkaProcessor {

    @Bean
    public Function<KStream<String, Location>,KStream<String, Location>> locationProcessor() {
        System.out.println("######STREAM");
        return kstream -> kstream.filter((key, location) -> {
            System.out.println(location);
            return location.getLatitude() > 10;
        });
    }
}
