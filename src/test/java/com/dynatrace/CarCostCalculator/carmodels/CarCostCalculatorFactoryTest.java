package com.dynatrace.CarCostCalculator.carmodels;

import org.junit.jupiter.api.Test;

import static com.dynatrace.CarCostCalculator.models.VehicleType.COUPE;
import static com.dynatrace.CarCostCalculator.models.VehicleType.LUXURY_SEDAN;
import static com.dynatrace.CarCostCalculator.models.VehicleType.SUV;
import static com.dynatrace.CarCostCalculator.models.VehicleType.TRUCK;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CarCostCalculatorFactoryTest {
    CarCostCalculatorFactory carCostCalculatorFactory = new CarCostCalculatorFactory();
    @Test
    void getCar_shouldReturnCoupeObject_whenTypeIsCoupe() {
        assertTrue(carCostCalculatorFactory.getCar(COUPE.getName()) instanceof Coupe);
    }

    @Test
    void getCar_shouldReturnSuvObject_whenTypeIsSuv() {
        assertTrue(carCostCalculatorFactory.getCar(SUV.getName()) instanceof SUV);
    }

    @Test
    void getCar_shouldReturnTruckObject_whenTypeIsTruck() {
        assertTrue(carCostCalculatorFactory.getCar(TRUCK.getName()) instanceof Truck);
    }

    @Test
    void getCar_shouldReturnLuxurySedanObject_whenTypeIsLuxurySedan() {
        assertTrue(carCostCalculatorFactory.getCar(LUXURY_SEDAN.getName()) instanceof LuxurySedan);
    }
}