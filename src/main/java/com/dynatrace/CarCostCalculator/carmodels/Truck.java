package com.dynatrace.CarCostCalculator.carmodels;

import static com.dynatrace.CarCostCalculator.constants.Constants.AUTOMATIC;
import static com.dynatrace.CarCostCalculator.constants.Constants.NAVIGATION;
import static com.dynatrace.CarCostCalculator.constants.Constants.PREMIUM_AUDIO;
import static com.dynatrace.CarCostCalculator.constants.Constants.SUNROOF;
import static com.dynatrace.CarCostCalculator.constants.Constants.TOW_PACKAGE;
import static com.dynatrace.CarCostCalculator.constants.Constants.V_8;

public class Truck implements Car{

    double carCost = 0.0;
    @Override
    public double calculateCost(String[] options, String destinationZip) {
        carCost += 25000;
        for (String option : options) {
            if (option.equals(V_8)) {
                carCost += 6000;
            }
            if (option.equals(AUTOMATIC)) {
                carCost += 1500;
            }
            if (option.equals(PREMIUM_AUDIO)) {
                carCost += 1100;
            }
            if (option.equals(SUNROOF)) {
                carCost += 1000;
            }
            if (option.equals(TOW_PACKAGE)) {
                carCost += 550;
            }
            if (option.equals(NAVIGATION)) {
                carCost += 1000;
            }
        }

        double tax = slowTaxCalculationMethod(destinationZip) + 1000;
        carCost += tax;
        return carCost;
    }

    private static double slowTaxCalculationMethod(String destinationZip) {
        // the Thread.sleep cannot be removed
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
            // Do nothing
        }
        return 500;
    }
}
