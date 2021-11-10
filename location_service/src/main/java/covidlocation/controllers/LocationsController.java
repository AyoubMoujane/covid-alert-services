package covidlocation.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import covidlocation.models.Location;
import covidlocation.repositories.LocationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/locations")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})

public class LocationsController {

    @Autowired
    private LocationRepository locationRepository;

    /*@RequestMapping("byUsers/{id}")
    public List<Location> byUser(@PathVariable long id) {
        return locationRepository.findAllByUsersOrderByLocation_date(id);
    }
    */

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