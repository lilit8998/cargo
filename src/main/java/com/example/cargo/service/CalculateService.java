package com.example.cargo.service;

public interface CalculateService {

    double calculateDistance(double lat1, double lon1, double lat2, double lon2);

    double calculatePrice(double distance, Double size);

    double parseParcelSize(String parcelSize);
}
