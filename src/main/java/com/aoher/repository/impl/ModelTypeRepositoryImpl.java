package com.aoher.repository.impl;

import com.aoher.model.ModelType;
import com.aoher.repository.ModelTypeRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ModelTypeRepositoryImpl implements ModelTypeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ModelType create(ModelType mt) {
        entityManager.persist(mt);
        entityManager.flush();
        return mt;
    }

    public ModelType update(ModelType mt) {
        mt = entityManager.merge(mt);
        entityManager.flush();
        return mt;
    }

    public void delete(ModelType mt) {
        entityManager.remove(mt);
        entityManager.flush();
    }

    public ModelType find(Long id) {
        return entityManager.find(ModelType.class, id);
    }
}