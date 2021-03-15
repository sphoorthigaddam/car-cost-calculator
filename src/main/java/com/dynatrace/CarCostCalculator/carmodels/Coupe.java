package com.dynatrace.CarCostCalculator.carmodels;


import com.dynatrace.CarCostCalculator.exception.OptionNotSupportedException;
import com.dynatrace.CarCostCalculator.models.Options;

import static com.dynatrace.CarCostCalculator.constants.Constants.AUTOMATIC;
import static com.dynatrace.CarCostCalculator.constants.Constants.NAVIGATION;
import static com.dynatrace.CarCostCalculator.constants.Constants.PREMIUM_AUDIO;
import static com.dynatrace.CarCostCalculator.constants.Constants.SUNROOF;
import static com.dynatrace.CarCostCalculator.constants.Constants.TOW_PACKAGE;
import static com.dynatrace.CarCostCalculator.constants.Constants.V_8;

public class Coupe implements Car {
    double carCost = 0.0;
    @Override
    public double calculateCost(String[] options, String destinationZip) {
        carCost += 15000;
        for (String option : options) {
            if (option.equals(V_8)) {
                carCost += 5000;
            } else if (option.equals(AUTOMATIC)) {
                carCost += 1000;
            } else if (option.equals(PREMIUM_AUDIO)) {
                carCost += 1000;
            } else if (option.equals(SUNROOF)) {
                carCost += 1000;
            } else if (option.equals(NAVIGATION)) {
                carCost += 1000;
            } else if (option.equals(TOW_PACKAGE)) {
                throw new OptionNotSupportedException(option+" not available for coupe.");
            }
        }
        return carCost;
    }

    @Override
    public double calculateCost(Options options, String destinationZip) {
        carCost += 15000;

        if(options != null) {
            if (options.getEngine() != null && options.getEngine().equals(V_8)) {
                carCost += 5000;
            }
            if (options.getTransmission() != null && options.getTransmission().equals(AUTOMATIC)) {
                carCost += 1000;
            }
            if (options.getAudio() != null && options.getAudio().equals(PREMIUM_AUDIO)) {
                carCost += 1000;
            }
            if (options.getRoofType() != null && options.getRoofType().equals(SUNROOF)) {
                carCost += 1000;
            }
            if (options.isNavigation()) {
                carCost += 1000;
            }
            if (options.isTowPackage()) {
                throw new OptionNotSupportedException("Towpackage option not available for coupe.");
            }
        }
        return carCost;
    }

}
