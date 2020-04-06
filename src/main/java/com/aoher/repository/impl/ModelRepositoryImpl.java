package com.aoher.repository.impl;

import com.aoher.model.Model;
import com.aoher.repository.ModelRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class ModelRepositoryImpl implements ModelRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Model create(Model mod) {
        entityManager.persist(mod);
        entityManager.flush();
        return mod;
    }

    @Override
    public Model update(Model mod) {
        mod = entityManager.merge(mod);
        entityManager.flush();
        return mod;
    }

    @Override
    public void delete(Model mod) {
        entityManager.remove(mod);
        entityManager.flush();
    }

    @Override
    public Model find(Long id) {
        return entityManager.find(Model.class, id);
    }

    @Override
    public List<Model> getModelsInPriceRange(BigDecimal lowest, BigDecimal highest) {
        @SuppressWarnings("unchecked")
        List<Model> mods = entityManager
                .createQuery("select m from Model m where m.price >= :lowest and m.price <= :highest")
                .setParameter("lowest", lowest)
                .setParameter("highest", highest).getResultList();
        return mods;
    }

    @Override
    public List<Model> getModelsByPriceRangeAndWoodType(BigDecimal lowest, BigDecimal highest, String wood) {
        @SuppressWarnings("unchecked")
        List<Model> mods = entityManager
                .createQuery("select m from Model m where m.price >= :lowest and m.price <= :highest and m.woodType like :wood")
                .setParameter("lowest", lowest)
                .setParameter("highest", highest)
                .setParameter("wood", "%" + wood + "%").getResultList();
        return mods;
    }

    @Override
    public List<Model> getModelsByType(String modelType) {
        @SuppressWarnings("unchecked")
        List<Model> mods = entityManager
                .createNamedQuery("Model.findAllModelsByType")
                .setParameter("name", modelType).getResultList();
        return mods;
    }

    @Override
    public Long getModelCount() {
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(Model.class)));

        return entityManager.createQuery(cq).getSingleResult();
    }
}
