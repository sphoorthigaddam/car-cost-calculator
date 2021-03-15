package com.dynatrace.CarCostCalculator.carmodels;

import com.dynatrace.CarCostCalculator.models.VehicleType;
import org.junit.jupiter.api.Test;

import static com.dynatrace.CarCostCalculator.constants.Constants.COUPE;
import static com.dynatrace.CarCostCalculator.constants.Constants.LUXURY_SEDAN;
import static com.dynatrace.CarCostCalculator.constants.Constants.SUV;
import static com.dynatrace.CarCostCalculator.constants.Constants.TRUCK;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CarCostCalculatorFactoryTest {
    @Test
    void getCar_shouldReturnCoupeObject_whenTypeIsCoupe() {
        assertTrue(VehicleType.getVehicleType(COUPE).getType() instanceof Coupe);
    }

    @Test
    void getCar_shouldReturnSuvObject_whenTypeIsSuv() {
        assertTrue(VehicleType.getVehicleType(SUV).getType() instanceof SUV);
    }

    @Test
    void getCar_shouldReturnTruckObject_whenTypeIsTruck() {
        assertTrue(VehicleType.getVehicleType(TRUCK).getType() instanceof Truck);
    }

    @Test
    void getCar_shouldReturnLuxurySedanObject_whenTypeIsLuxurySedan() {
        assertTrue(VehicleType.getVehicleType(LUXURY_SEDAN).getType() instanceof LuxurySedan);
    }
}