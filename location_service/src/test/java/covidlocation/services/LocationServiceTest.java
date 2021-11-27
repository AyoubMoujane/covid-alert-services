package covidlocation.services;

import covidlocation.repositories.LocationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
    void setUp() {
        AutoCloseable autoCloseable =MockitoAnnotations.openMocks(this);
        serviceTested = new LocationService(locationRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void addLocation() {
    }

    @Test
    void findAllLocations() {
        serviceTested.findAllLocations();
        verify(locationRepository).findAll();
    }

    @Test
    void findLocationByID() {
    }

    @Test
    void deleteLocation() {
    }

    @Test
    void updateLocation() {
    }

    @Test
    void findLocationByUser() {
    }
}