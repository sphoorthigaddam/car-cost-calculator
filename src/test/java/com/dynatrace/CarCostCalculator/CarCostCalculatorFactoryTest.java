package com.dynatrace.CarCostCalculator;

import com.dynatrace.CarCostCalculator.carmodels.Coupe;
import com.dynatrace.CarCostCalculator.carmodels.LuxurySedan;
import com.dynatrace.CarCostCalculator.carmodels.SUV;
import com.dynatrace.CarCostCalculator.carmodels.Truck;
import org.junit.jupiter.api.Test;

import static com.dynatrace.CarCostCalculator.constants.Constants.COUPE;
import static com.dynatrace.CarCostCalculator.constants.Constants.LUXURY_SEDAN;
import static com.dynatrace.CarCostCalculator.constants.Constants.SUV;
import static com.dynatrace.CarCostCalculator.constants.Constants.TRUCK;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CarCostCalculatorFactoryTest {
    CarCostCalculatorFactory carCostCalculatorFactory = new CarCostCalculatorFactory();
    @Test
    void getCar_shouldReturnCoupeObject_whenTypeIsCoupe() {
        assertTrue(carCostCalculatorFactory.getCar(COUPE) instanceof Coupe);
    }

    @Test
    void getCar_shouldReturnSuvObject_whenTypeIsSuv() {
        assertTrue(carCostCalculatorFactory.getCar(SUV) instanceof SUV);
    }

    @Test
    void getCar_shouldReturnTruckObject_whenTypeIsTruck() {
        assertTrue(carCostCalculatorFactory.getCar(TRUCK) instanceof Truck);
    }

    @Test
    void getCar_shouldReturnLuxurySedanObject_whenTypeIsLuxurySedan() {
        assertTrue(carCostCalculatorFactory.getCar(LUXURY_SEDAN) instanceof LuxurySedan);
    }
}