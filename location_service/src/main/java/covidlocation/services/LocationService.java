package covidlocation.services;

import covidlocation.models.Location;
import covidlocation.repositories.LocationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import covidlocation.services.Producer;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
public class LocationService {


    private LocationRepository locationRepository;
    private final Producer producer;
    private final Consumer consumer;


    @Autowired
    public LocationService(LocationRepository locationRepository, Producer producer,Consumer consumer){
        this.locationRepository = locationRepository;
        this.producer = producer;
        this.consumer = consumer;
    }


    public Location addLocation(Location location) {
        this.producer.saveCreateLocationLog(location);
        return  locationRepository.saveAndFlush(location);
    }

    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }


    public Location findLocationByID(Long id) {
        if(locationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location with ID "+id+" not found");
        }
        return locationRepository.findById(id).get();
    }


    public void deleteLocation(Long id) {
        if(locationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location with ID "+id+" not found");
        }
        locationRepository.deleteById(id);
    }


    public Location updateLocation(Long id,Location location) {
        if(locationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location with ID "+id+" not found");
        }
        Location existingUser = locationRepository.findById(id).get();
        BeanUtils.copyProperties(location,existingUser,"location_id");
        return locationRepository.saveAndFlush(existingUser);
    }

    public List<Location> findLocationByUser(long id) {
        try {
            return locationRepository.getUserLocation(id);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID "+id+" not found");
        }
    }

    public Set<Long> findUsersAtRisk(long id,long MAX_DISTANCE,long CONTAGION_TIME) {
        try {
            List<Location> locations = this.getUserLocationFromKafka(id);
            System.out.println("getUserLocationFromKafka :" + id + ", locations:"+ locations.toString());
            Set<Long> usersAtRisk = this.getNearUserKafka(locations, MAX_DISTANCE, CONTAGION_TIME);
            return usersAtRisk;
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw exception;

        }
    }

    public List<Location> getUserLocationFromKafka(long id) {
        List<Location> locations = consumer.getLocations();
        Predicate<Location> byLocation = location -> location.getUser().getUser_id() == id;
        List<Location> result = locations.stream().filter(byLocation)
                .collect(Collectors.toList());

        return result;

    }

    public List<Long> getNearUserByLocationKafka(Location location,long MAX_DISTANCE,long CONTAGION_TIME) {
        List<Location> locations = consumer.getLocations();
        System.out.println("consumer locations  : " + locations.toString());

        List<Long> users = new ArrayList<>();
        for (Location loc :locations){
            if (loc.isCloseto(location,MAX_DISTANCE) && loc.isMoreRecentThan(CONTAGION_TIME))
                users.add(location.getUser().getUser_id());
        }
        System.out.println("getNearUserByLocationKafka : " + users.toString());
        return  users;
    }

    public Set<Long> getNearUserKafka(List<Location> locations,long MAX_DISTANCE,long CONTAGION_TIME) {
        Set<Long> nearUsers = new LinkedHashSet<>();
        for (Location location :locations){
            nearUsers.addAll(this.getNearUserByLocationKafka(location,MAX_DISTANCE, CONTAGION_TIME));
        }
        return nearUsers;
    }



}
