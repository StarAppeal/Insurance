package de.starappeal.versicherung.controller.request;

public record CalculateInsuranceRequest(int kilometer, String zipcode, String vehicleType) {}
