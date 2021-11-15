package covidlocation.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import covidlocation.models.Location;
import covidlocation.models.User;
import covidlocation.repositories.LocationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    private LocationRepository locationRepository;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody final Location location) {
        System.out.println(location);
        return  locationRepository.saveAndFlush(location);
    }

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



}