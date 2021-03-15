package com.dynatrace.CarCostCalculator.models;

import com.dynatrace.CarCostCalculator.carmodels.Car;
import com.dynatrace.CarCostCalculator.carmodels.Coupe;
import com.dynatrace.CarCostCalculator.carmodels.LuxurySedan;
import com.dynatrace.CarCostCalculator.carmodels.SUV;
import com.dynatrace.CarCostCalculator.carmodels.Truck;
import com.dynatrace.CarCostCalculator.exception.RequestNotValidException;

import java.util.Arrays;

public enum VehicleType {
    COUPE("coupe" , new Coupe()),
    TRUCK("Truck", new Truck()),
    SUV("suv", new SUV()),
    LUXURY_SEDAN("luxury_sedan", new LuxurySedan());

    private String name;
    private Car type;

    VehicleType(String name, Car type) {
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return this.name;
    }

    public Car getType(){
        return this.type;
    }

    public static VehicleType getVehicleType(String name) {
        return Arrays.stream(VehicleType.values())
                .filter(type -> type.name.equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(() -> new RequestNotValidException(name + " car type not available at the moment!"));
    }
}
