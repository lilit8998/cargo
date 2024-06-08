package com.example.cargo.service;

import com.example.cargo.service.impl.CalculateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class CalculateServiceTest {
    @InjectMocks
    private CalculateService service;

    @BeforeEach
    void setUp() {
        service = new CalculateServiceImpl();
    }

    @Test
    void calculateDistance() {
        double lat1 = 52.5200; // Berlin
        double lon1 = 13.4050;
        double lat2 = 48.8566; // Paris
        double lon2 = 2.3522;

        double distance = service.calculateDistance(lat1, lon1, lat2, lon2);
        assertTrue(distance > 0);
    }


    @Test
    void parseParcelSize() {
        String size = "12.5";
        double parsedSize = service.parseParcelSize(size);
        assertEquals(12.5, parsedSize);
    }

    @Test
    void parseParcelSizeWithInvalidFormat() {
        String size = "invalid";

        assertThrows(IllegalArgumentException.class, () -> service.parseParcelSize(size));
    }

    @Test
    void parseParcelSizeWithEmptyString() {
        String size = "";

        assertThrows(IllegalArgumentException.class, () -> service.parseParcelSize(size));
    }

}