package covidlocation.services;

import covidlocation.repositories.LocationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class LocationServiceTest {
    @Mock
    private LocationService serviceTested;
    private AutoCloseable autoCloseable;
    private LocationRepository locationRepository;

    @BeforeEach
    @Disabled
    void setUp() {
        /*AutoCloseable autoCloseable =MockitoAnnotations.openMocks(this);
        serviceTested = new LocationService(locationRepository);

         */
    }

    @AfterEach
    @Disabled
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    @Disabled
    void addLocation() {
    }

    @Test
    @Disabled
    void findAllLocations() {
        serviceTested.findAllLocations();
        verify(locationRepository).findAll();
    }

    @Test
    @Disabled
    void findLocationByID() {
    }

    @Test
    @Disabled
    void deleteLocation() {
    }

    @Test
    @Disabled
    void updateLocation() {
    }

    @Test
    @Disabled
    void findLocationByUser() {
    }
}