package covidlocation.repositories;

import covidlocation.models.Location;
import covidlocation.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface LocationCustomRepository {

    List<Location> getUserLocation(long id);
    List<Integer> getNearUserByLocation(Location location);
    Set<Integer> getNearUser(List<Location> location);
    User getUser(long id);


}