package com.dynatrace.CarCostCalculator.carmodels;


import com.dynatrace.CarCostCalculator.exception.RequestNotValidException;
import org.springframework.stereotype.Component;

import static com.dynatrace.CarCostCalculator.models.VehicleType.COUPE;
import static com.dynatrace.CarCostCalculator.models.VehicleType.LUXURY_SEDAN;
import static com.dynatrace.CarCostCalculator.models.VehicleType.SUV;
import static com.dynatrace.CarCostCalculator.models.VehicleType.TRUCK;

@Component
public class CarCostCalculatorFactory {


    public Car getCar(String carType) {
        if (COUPE.getName().equalsIgnoreCase(carType)) {
            return new Coupe();
        } else if (TRUCK.getName().equalsIgnoreCase(carType)) {
            return new Truck();
        } else if (SUV.getName().equalsIgnoreCase(carType)) {
            return new SUV();
        } else if (LUXURY_SEDAN.getName().equalsIgnoreCase(carType)) {
            return new LuxurySedan();
        } else {
            throw new RequestNotValidException(carType + " car type not available at the moment!");
        }
    }
}
