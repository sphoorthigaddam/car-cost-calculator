package com.dynatrace.CarCostCalculator.carmodels;

import com.dynatrace.CarCostCalculator.models.Options;

import static com.dynatrace.CarCostCalculator.constants.Constants.AUTOMATIC;
import static com.dynatrace.CarCostCalculator.constants.Constants.PREMIUM_AUDIO;
import static com.dynatrace.CarCostCalculator.constants.Constants.SUNROOF;
import static com.dynatrace.CarCostCalculator.constants.Constants.V_8;

public class Truck implements Car{

    private double carCost = 0.0;

    @Override
    public double calculateCost(Options options, String destinationZip) {
        carCost += 25000;

        if(options != null) {
            if (options.getEngine() != null && options.getEngine().equals(V_8)) {
                carCost += 6000;
            }
            if (options.getTransmission() != null && options.getTransmission().equals(AUTOMATIC)) {
                carCost += 1500;
            }
            if (options.getAudio() != null && options.getAudio().equals(PREMIUM_AUDIO)) {
                carCost += 1100;
            }
            if (options.getRoofType() != null && options.getRoofType().equals(SUNROOF)) {
                carCost += 1000;
            }
            if (options.isNavigation()) {
                carCost += 1000;
            }
            if (options.isTowPackage()) {
                carCost += 550;
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
