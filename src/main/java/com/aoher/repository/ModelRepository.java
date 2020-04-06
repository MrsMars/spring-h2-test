package com.aoher.repository;

import com.aoher.model.Model;

import java.math.BigDecimal;
import java.util.List;

public interface ModelRepository {

    /**
     * Create
     */
    public Model create(Model mod);

    /**
     * Update
     */
    public Model update(Model mod);

    /**
     * Delete
     */
    public void delete(Model mod);

    /**
     * Find
     */
    public Model find(Long id);

    /**
     * Custom finder
     */
    public List<Model> getModelsInPriceRange(BigDecimal lowest, BigDecimal highest);

    /**
     * Custom finder
     */
    public List<Model> getModelsByPriceRangeAndWoodType(BigDecimal lowest, BigDecimal highest, String wood);

    /**
     * NamedQuery finder
     */
    public List<Model> getModelsByType(String modelType);

    /**
     * Count
     */
    public Long getModelCount();
}
