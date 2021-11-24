package covidlocation.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import covidlocation.models.PositiveUser;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class CustomObjectDeserializer implements Deserializer<PositiveUser> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public PositiveUser deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        PositiveUser object = null;
        try {
            object = mapper.readValue(data, PositiveUser.class);
        } catch (Exception exception) {
            System.out.println("Error in deserializing bytes "+ exception);
        }
        return object;
    }

    @Override
    public void close() {
    }
}
