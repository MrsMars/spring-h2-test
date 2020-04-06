package com.aoher.repository;

import com.aoher.model.Manufacturer;

import java.util.Date;
import java.util.List;

public interface ManufacturerRepository {

    /**
     * Create
     */
    Manufacturer create(Manufacturer man);

    /**
     * Update
     */
    Manufacturer update(Manufacturer man);

    /**
     * Delete
     */
    void delete(Manufacturer man);

    /**
     * Find
     */
    Manufacturer find(Long id);

    /**
     * Custom finder
     */
    List<Manufacturer> getManufacturersFoundedBeforeDate(Date date);

    /**
     * Custom finder
     */
    Manufacturer getManufacturerByName(String name);

    /**
     * Native Query finder
     */
    List<Manufacturer> getManufacturersThatSellModelsOfType(String modelType);
}
