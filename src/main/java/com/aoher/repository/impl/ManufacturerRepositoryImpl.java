package com.aoher.repository.impl;

import com.aoher.model.Manufacturer;
import com.aoher.repository.ManufacturerRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
public class ManufacturerRepositoryImpl implements ManufacturerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Manufacturer create(Manufacturer man) {
        entityManager.persist(man);
        entityManager.flush();
        return man;
    }

    public Manufacturer update(Manufacturer man) {
        man = entityManager.merge(man);
        entityManager.flush();
        return man;
    }

    public void delete(Manufacturer man) {
        entityManager.remove(man);
        entityManager.flush();
    }

    public Manufacturer find(Long id) {
        return entityManager.find(Manufacturer.class, id);
    }

    public List<Manufacturer> getManufacturersFoundedBeforeDate(Date date) {
        @SuppressWarnings("unchecked")
        List<Manufacturer> mans = entityManager
                .createQuery("select m from Manufacturer m where m.foundedDate < :date")
                .setParameter("date", date).getResultList();
        return mans;
    }

    public Manufacturer getManufacturerByName(String name) {
        return (Manufacturer)entityManager
                .createQuery("select m from Manufacturer m where m.name like :name")
                .setParameter("name", name + "%").getSingleResult();
    }

    public List<Manufacturer> getManufacturersThatSellModelsOfType(String modelType) {
        @SuppressWarnings("unchecked")
        List<Manufacturer> mans = entityManager
                .createNamedQuery("Manufacturer.getAllThatSellAcoustics")
                .setParameter(1, modelType).getResultList();
        return mans;
    }
}