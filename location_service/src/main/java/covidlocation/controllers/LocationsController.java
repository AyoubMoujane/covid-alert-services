package covidlocation.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import covidlocation.models.Location;
import covidlocation.models.User;
import covidlocation.repositories.LocationRepository;
import covidlocation.services.LocationService;
import covidlocation.services.Producer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/locations")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})

public class LocationsController {

    private final Producer producer;

    @Autowired
    private final LocationService locationService;

    @Autowired
    private LocationRepository locationRepository;

    public LocationsController(Producer producer, LocationService locationService) {
        this.producer = producer;
        this.locationService = locationService;
    }

    @GetMapping
    public List<Location> list() {
        return locationService.findAllLocations();
    }


    @GetMapping
    @RequestMapping("{id}")
    public Location get(@PathVariable Long id) {
        return locationService.findLocationByID(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody Location location) {
        return  locationService.addLocation(location);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        locationService.deleteLocation(id);
    }

    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public Location update(@PathVariable Long id, @RequestBody Location location) {
        return locationService.updateLocation(id,location);
    }

    @RequestMapping("by_user/{id}")
    public List<Location> byUser(@PathVariable("id") long id) {
        return locationService.findLocationByUser(id);
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


    @RequestMapping("test/{id}/{MAX_DISTANCE}/{CONTAGION_TIME}")
    public Set<Long> getUsersAtRisk(@PathVariable("id") long id,@PathVariable("MAX_DISTANCE") long MAX_DISTANCE,@PathVariable("CONTAGION_TIME") long CONTAGION_TIME) {
        return locationService.findUsersAtRisk(id,MAX_DISTANCE,CONTAGION_TIME);
    }



}