package com.dynatrace.CarCostCalculator.service;

import com.dynatrace.CarCostCalculator.carmodels.Car;
import com.dynatrace.CarCostCalculator.models.CarCalculatorCostRequest;
import com.dynatrace.CarCostCalculator.models.CarCostCalculatorResponse;
import com.dynatrace.CarCostCalculator.models.VehicleType;
import org.springframework.stereotype.Service;

@Service
public class CarCostCalculatorService {

    public CarCostCalculatorResponse calculateCarCost(CarCalculatorCostRequest request) {
        Car car = VehicleType.getVehicleType(request.getType()).getType();

        double carCost = car.calculateCost(request.getSelectedOptions(), request.getDestinationZip());

        carCost = addLuxuryTax(carCost);

        return CarCostCalculatorResponse.builder().cost(carCost).build();
    }

    private double addLuxuryTax(double carCost) {
        if (carCost > 30000 && carCost < 60000) {
            // luxury tax
            carCost += 850;
        } else if (carCost > 60000) {
            // extra luxury tax
            carCost += 1000;
        }
        return carCost;
    }
}
