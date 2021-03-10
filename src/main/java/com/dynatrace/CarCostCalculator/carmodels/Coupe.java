package com.dynatrace.CarCostCalculator.carmodels;


import static com.dynatrace.CarCostCalculator.constants.Constants.AUTOMATIC;
import static com.dynatrace.CarCostCalculator.constants.Constants.NAVIGATION;
import static com.dynatrace.CarCostCalculator.constants.Constants.PREMIUM_AUDIO;
import static com.dynatrace.CarCostCalculator.constants.Constants.SUNROOF;
import static com.dynatrace.CarCostCalculator.constants.Constants.TOW_PACKAGE;
import static com.dynatrace.CarCostCalculator.constants.Constants.V_8;

public class Coupe implements Car {
    double carCost = 0.0;
    @Override
    public double calculateCost(String[] options, String destinationZip) throws Exception{
        carCost += 15000;
        for (String option : options) {
            if (option.equals(V_8)) {
                carCost += 5000;
            }

            if (option.equals(AUTOMATIC)) {
                carCost += 1000;
            }

            if (option.equals(PREMIUM_AUDIO)) {
                carCost += 1000;
            }

            if (option.equals(SUNROOF)) {
                carCost += 1000;
            }

            if (option.equals(NAVIGATION)) {
                carCost += 1000;
            }
        }
        for (String option : options) {
            if (option.equals(TOW_PACKAGE)) {
                throw new Exception("Not available for coupe.");
            }
        }
        return carCost;
    }

}
