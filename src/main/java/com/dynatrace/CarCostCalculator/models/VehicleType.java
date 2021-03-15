package com.dynatrace.CarCostCalculator.models;

public enum VehicleType {
    COUPE("coupe"),
    TRUCK("Truck"),
    SUV("suv"),
    LUXURY_SEDAN("luxury_sedan");

    private final String name;

    VehicleType(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
