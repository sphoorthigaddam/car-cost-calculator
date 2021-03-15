package com.dynatrace.CarCostCalculator.carmodels;


import com.dynatrace.CarCostCalculator.models.Options;

import static com.dynatrace.CarCostCalculator.constants.Constants.AUTOMATIC;
import static com.dynatrace.CarCostCalculator.constants.Constants.NAVIGATION;
import static com.dynatrace.CarCostCalculator.constants.Constants.PREMIUM_AUDIO;
import static com.dynatrace.CarCostCalculator.constants.Constants.SUNROOF;
import static com.dynatrace.CarCostCalculator.constants.Constants.TOW_PACKAGE;
import static com.dynatrace.CarCostCalculator.constants.Constants.V_8;

public class SUV implements Car{

    double carCost = 0.0;

    @Override
    public double calculateCost(String[] options, String destinationZip) {
        carCost += 30000;
        for (String option : options) {
            if (option.equals(V_8)) {
                carCost += 5500;
            } else if (option.equals(AUTOMATIC)) {
                carCost += 1200;
            } else if (option.equals(PREMIUM_AUDIO)) {
                carCost += 1500;
            } else if (option.equals(SUNROOF)) {
                carCost += 1000;
            } else if (option.equals(TOW_PACKAGE)) {
                carCost += 500;
            } else if (option.equals(NAVIGATION)) {
                carCost += 1000;
            }
        }
        double tax = slowTaxCalculationMethod(destinationZip) + 1000;
        carCost += tax;
        return carCost;
    }

    @Override
    public double calculateCost(Options options, String destinationZip) {
        carCost += 30000;

        if(options != null) {
            if (options.getEngine() != null && options.getEngine().equals(V_8)) {
                carCost += 5500;
            }

            if (options.getTransmission() != null && options.getTransmission().equals(AUTOMATIC)) {
                carCost += 1200;
            }

            if (options.getAudio() != null && options.getAudio().equals(PREMIUM_AUDIO)) {
                carCost += 1500;
            }

            if (options.getRoofType() != null && options.getRoofType().equals(SUNROOF)) {
                carCost += 1000;
            }

            if (options.isNavigation()) {
                carCost += 1000;
            }

            if (options.isTowPackage()) {
                carCost += 500;
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
