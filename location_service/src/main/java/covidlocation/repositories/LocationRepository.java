package covidlocation.repositories;

import covidlocation.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location,Long> {

    //List<Location> findAllByUsersOrderByLocation_date(long id);

}
