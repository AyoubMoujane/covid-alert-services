package covidlocation.repositories;

import covidlocation.models.Location;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class LocationRepositoryImpl implements LocationCustomRepository {

    private final int MAX_DISTANCE = 20000;
    private final double COEFFICIENT = 69.1;
    private final double DENOMINATOR = 57.3;
    private final int CONTAGION_TIME = 7;

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Location> getUserLocation(long id) {
        String sql = "SELECT loc FROM Location loc Where user_id = :id";
        TypedQuery<Location> query = entityManager.createQuery(sql, Location.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Integer> getNearUserByLocation(Location location) {
        String sql = "SELECT user_id FROM (SELECT user_id,longitude,latitude, SQRT( POW(:coefficient * (latitude - :start_latitude), 2) + POW(:coefficient * (:start_longitude - longitude) * COS(latitude / :denominator), 2)) AS distance,(CAST(now() as date) - CAST(location_date as date) ) AS nb_days FROM Locations ORDER BY distance) location_distance WHERE distance < :max_distance and nb_days< :contagion_time";
        String hql = "SELECT loc, SQRT( POW(69.1 * (latitude - :start_latitude), 2) + POW(69.1 * (:start_longitude - longitude) * COS(latitude / 57.3), 2)) AS distance FROM Locations loc WHERE distance < :max_distance ORDER BY distance";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("start_latitude", location.getLatitude());
        query.setParameter("start_longitude", location.getLongitude());
        query.setParameter("max_distance", MAX_DISTANCE);
        query.setParameter("coefficient", COEFFICIENT);
        query.setParameter("denominator", DENOMINATOR);
        query.setParameter("contagion_time", CONTAGION_TIME);

        System.out.println(query.getResultList());


        return query.getResultList();
    }

    @Override
    public Set<Integer> getNearUser(List<Location> locations) {
        Set<Integer> nearUsers = new LinkedHashSet<>();
        for (Location location :locations){
            if (!this.getNearUserByLocation(location).isEmpty())
            nearUsers.addAll(this.getNearUserByLocation(location));
        }



        return nearUsers;
    }


}