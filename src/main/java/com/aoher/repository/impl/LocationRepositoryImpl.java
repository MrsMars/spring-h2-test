package com.aoher.repository.impl;

import com.aoher.model.Location;
import com.aoher.repository.LocationRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class LocationRepositoryImpl implements LocationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Location create(Location loc) {
        entityManager.persist(loc);
        entityManager.flush();
        return loc;
    }

    public Location update(Location loc) {
        loc = entityManager.merge(loc);
        entityManager.flush();
        return loc;
    }

    public void delete(Location loc) {
        entityManager.remove(loc);
        entityManager.flush();
    }

    public Location find(Long id) {
        return entityManager.find(Location.class, id);
    }

    public List<Location> getLocationByStateName(String name) {
        @SuppressWarnings("unchecked")
        List<Location> locations = entityManager
                .createQuery("select l from Location l where l.state like :state")
                .setParameter("state", name + "%").getResultList();
        return locations;
    }
}