package covidlocation.repositories;

import covidlocation.models.Location;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationCustomRepository {

    List<Location> getUserLocation(long id);

}