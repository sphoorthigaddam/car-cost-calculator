package com.dynatrace.CarCostCalculator.carmodels;

import com.dynatrace.CarCostCalculator.constants.Constants;

public class Coupe {
    double carCost = 0.0;
    public double calculateCost(String[] options) throws Exception{
        carCost += 15000;
        for (String option : options) {
            if (option.equals(Constants.V_8)) {
                carCost += 5000;
            }
        }
        for (String option : options) {
            if (option.equals(Constants.AUTOMATIC)) {
                carCost += 1000;
            }
        }
        for (String option : options) {
            if (option.equals(Constants.PREMIUMAUDIO)) {
                carCost += 1000;
            }
        }
        for (String option : options) {
            if (option.equals(Constants.SUNROOF)) {
                carCost += 1000;
            }
        }
        for (String option : options) {
            if (option.equals(Constants.NAVIGATION)) {
                carCost += 1000;
            }
        }
        for (String option : options) {
            if (option.equals(Constants.TOWPACKAGE)) {
                throw new Exception("Not available for coupe.");
            }
        }
        return carCost;
    }
}
