package com.dynatrace.CarCostCalculator;


import com.dynatrace.CarCostCalculator.carmodels.Car;
import com.dynatrace.CarCostCalculator.carmodels.Coupe;
import com.dynatrace.CarCostCalculator.carmodels.LuxurySedan;
import com.dynatrace.CarCostCalculator.carmodels.SUV;
import com.dynatrace.CarCostCalculator.carmodels.Truck;
import org.springframework.stereotype.Component;

import static com.dynatrace.CarCostCalculator.constants.Constants.COUPE;
import static com.dynatrace.CarCostCalculator.constants.Constants.LUXURY_SEDAN;
import static com.dynatrace.CarCostCalculator.constants.Constants.SUV;
import static com.dynatrace.CarCostCalculator.constants.Constants.TRUCK;

@Component
public class CarCostCalculatorFactory {


    public Car getCar(String carType) {
        if(carType.equals(COUPE)) {
            return new Coupe();
        }

        if(carType.equals(TRUCK)) {
            return new Truck();
        }

        if(carType.equals(SUV)) {
            return new SUV();
        }

        if(carType.equals(LUXURY_SEDAN)) {
            return new LuxurySedan();
        }

        return null;
    }
}
