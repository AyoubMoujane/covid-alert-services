package covidlocation.controllers;

import covidlocation.models.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {
    private String USER_SERVICE_URL = "http://localhost:5001/users";

    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public User getUserJSON(String id) {
        String url = USER_SERVICE_URL+"/{id}";
        return this.restTemplate.getForObject(url, User.class,id);
    }
    public String getUserPlainJSON(String id) {
        String url = USER_SERVICE_URL+"/{id}";
        return this.restTemplate.getForObject(url, String.class,id);
    }
}