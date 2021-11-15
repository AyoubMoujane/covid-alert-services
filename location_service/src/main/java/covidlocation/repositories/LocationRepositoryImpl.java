package covidlocation.repositories;

import covidlocation.models.Location;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class LocationRepositoryImpl implements LocationCustomRepository {

    private final int MAX_DISTANCE = 200;

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Location> getUserLocation(long id) {
        String sql = "SELECT loc FROM Location loc Where user_id = :id";
        TypedQuery<Location> query = entityManager.createQuery(sql, Location.class);
        query.setParameter("id", id);
        return query.getResultList();
    }


}