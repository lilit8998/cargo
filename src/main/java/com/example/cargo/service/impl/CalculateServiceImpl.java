package com.example.cargo.service.impl;

import com.example.cargo.service.CalculateService;
import org.springframework.stereotype.Service;

@Service
public class CalculateServiceImpl implements CalculateService {
    private static final double EARTH_RADIUS = 6371;

    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        double a = Math.pow(Math.sin(deltaLat / 2), 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.pow(Math.sin(deltaLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    public double calculatePrice(double distance, Double size) {
        double basePrice = distance * 25;
        double sizePrice = 0.0;

        if (size != null) {
            if (size.equals(1.0)) {
                sizePrice = 100;
            } else if (size.equals(2.0)) {
                sizePrice = 150;
            } else if (size.equals(30.0)) {
                sizePrice = 200;
            }
        }

        return basePrice + sizePrice;
    }

    public double parseParcelSize(String parcelSize) {
        String numericPart = parcelSize.replaceAll("[^\\d.]", "");
        return Double.parseDouble(numericPart);
    }
}
