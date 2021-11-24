package covidtest.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import covidtest.models.PositiveUser;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class CustumSerializer implements Serializer<PositiveUser> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, PositiveUser data) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception exception) {
            System.out.println("Error in serializing object"+ data);
        }
        return retVal;
    }

    @Override
    public void close() {

    }
}
