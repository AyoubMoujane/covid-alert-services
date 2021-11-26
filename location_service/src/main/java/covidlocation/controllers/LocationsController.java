package covidlocation.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import covidlocation.models.Location;
import covidlocation.models.User;
import covidlocation.models.UserAtRisk;
import covidlocation.repositories.LocationRepository;
import covidlocation.services.Producer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/locations")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})

public class LocationsController {

    @Autowired
    private RestService restService;


    @Autowired
    private LocationRepository locationRepository;

    private final Producer producer;

    public LocationsController(Producer producer) {
        this.producer = producer;
    }

    @RequestMapping("by_user/{id}")
    public List<Location> byUser(@PathVariable("id") long id) {
        System.out.println("coucou");

        try {
            return locationRepository.getUserLocation(id);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw exception;

        }
    }

    @PostMapping
    @RequestMapping("/near_users")
    public Set<Integer> getNearUser(@RequestBody final List<Location> location) {
        System.out.println(location);
        try {
            System.out.println(locationRepository.getNearUser(location));
            return locationRepository.getNearUser(location);
        }
        catch (Exception exception) {
            System.out.println("Error in getNearUser");
            System.out.println(exception.getMessage());
            throw exception;

        }
    }


    @RequestMapping("users_at_risk/{id}")
    public Set<Integer> getUsersAtRisk(@PathVariable("id") long id) {

        try {
            List<Location> locations = new ArrayList<>();
            locations = locationRepository.getUserLocation(id);
            return locationRepository.getNearUser(locations);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw exception;

        }
    }




    @GetMapping
    public List<Location> list() {
        return locationRepository.findAll();
    }


    @GetMapping
    @RequestMapping("{id}")
    public Location get(@PathVariable Long id) {
        if(locationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location with ID "+id+" not found");
        }
        return locationRepository.findById(id).get();
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Location create(@RequestBody Location location) {
//
//        User user = locationRepository.getUser(location.getUser().getUser_id());
//        location.setUser(user);
//        return  locationRepository.saveAndFlush(location);
//
//
//    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        // TODO: Ajouter ici une validation si tous les champs ont ete passesSinon, retourner une erreur 400 (Bad Payload)
        if(locationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location with ID "+id+" not found");
        }
        locationRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public Location update(@PathVariable Long id, @RequestBody Location location) {
        // TODO: Ajouter ici une validation si tous les champs ont ete passes Sinon, retourner une erreur 400 (Bad Payload)
        if(locationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location with ID "+id+" not found");
        }
        Location existingUser = locationRepository.findById(id).get();
        BeanUtils.copyProperties(location,existingUser,"location_id");
        return locationRepository.saveAndFlush(existingUser);
    }

    @PostMapping("post_message")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendUser() {
        List<UserAtRisk> listUserAtRisks = new ArrayList<>();

        listUserAtRisks.add(new UserAtRisk("1"));
        listUserAtRisks.add(new UserAtRisk("2"));
        listUserAtRisks.add(new UserAtRisk("3"));
        listUserAtRisks.add(new UserAtRisk("4"));
        producer.sendUserAtRiskMessage(listUserAtRisks);
    }



}