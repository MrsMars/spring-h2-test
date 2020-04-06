package com.aoher.repository;

import com.aoher.model.ModelType;

public interface ModelTypeRepository {

    /**
     * Create
     */
    ModelType create(ModelType mt);

    /**
     * Update
     */
    ModelType update(ModelType mt);

    /**
     * Delete
     */
    void delete(ModelType mt);

    /**
     * Find
     */
    ModelType find(Long id);
}
