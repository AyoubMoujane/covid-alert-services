package covidlocation.repositories;

import covidlocation.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LocationRepository extends JpaRepository<Location,Long> {



    //@Query(value = "SELECT location_id FROM locations WHERE user = ?1", nativeQuery = true)
    //List<Location> findByUser(long id);

    //@Query(value = "SELECT user_id FROM Locations WHERE user_id = ?1", nativeQuery = true)
    //Array[long] findNearUser(List<Location> id);


}
