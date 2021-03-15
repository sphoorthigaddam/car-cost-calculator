package com.dynatrace.CarCostCalculator.carmodels;


import com.dynatrace.CarCostCalculator.models.VehicleType;
import org.springframework.stereotype.Component;

@Component
public class CarCostCalculatorFactory {
    public Car getCar(String carType) {
        return VehicleType.getVehicleType(carType).getType();
    }
}
