package com.aoher;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.aoher.model.Location;
import com.aoher.repository.LocationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = {"classpath:com/aoher/db/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class LocationPersistenceTests {

    @Autowired
    private LocationRepository locationRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testSaveAndGetAndDelete() {
        Location location = new Location();
        location.setCountry("Canada");
        location.setState("British Columbia");
        location = locationRepository.create(location);

        entityManager.clear();

        Location otherLocation = locationRepository.find(location.getId());
        assertEquals("Canada", otherLocation.getCountry());
        assertEquals("British Columbia", otherLocation.getState());

        locationRepository.delete(otherLocation);
    }

    @Test
    public void testFindWithLike() {
        List<Location> locs = locationRepository.getLocationByStateName("New");

        assertEquals(4, locs.size());
    }

    @Test
    @Transactional  //note this is needed because we will get a lazy load exception unless we are in a tx
    public void testFindWithChildren() {
        Location arizona = locationRepository.find(3L);

        assertEquals("United States", arizona.getCountry());
        assertEquals("Arizona", arizona.getState());
        assertEquals(1, arizona.getManufacturers().size());
        assertEquals("Fender Musical Instruments Corporation", arizona.getManufacturers().get(0).getName());
    }
}