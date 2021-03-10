package com.dynatrace.CarCostCalculator.service;

import com.dynatrace.CarCostCalculator.CarCostCalculatorFactory;
import com.dynatrace.CarCostCalculator.carmodels.Car;
import com.dynatrace.CarCostCalculator.models.CalculateCarCostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarCostCalculatorService {

    @Autowired
    CarCostCalculatorFactory factory;

    public double calculateCarCost(CalculateCarCostRequest request) throws Exception {
        Car car = factory.getCar(request.type);

        double carCost = car.calculateCost(request.selectedOptions, request.destinationZip);


        carCost = addLuxuryTax(carCost);

        return carCost;
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
