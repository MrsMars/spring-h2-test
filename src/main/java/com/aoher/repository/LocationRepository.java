package com.aoher.repository;

import com.aoher.model.Location;

import java.util.List;

public interface LocationRepository {

    /**
     * Create
     */
    Location create(Location loc);

    /**
     * Update
     */
    Location update(Location loc);

    /**
     * Delete
     */
    void delete(Location loc);

    /**
     * Find
     */
    Location find(Long id);

    /**
     * Custom finder
     */
    List<Location> getLocationByStateName(String name);
}
