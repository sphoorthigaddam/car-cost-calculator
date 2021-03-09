package com.dynatrace.CarCostCalculator.carmodels;

import com.dynatrace.CarCostCalculator.constants.Constants;

public class SUV {

    double carCost = 0.0;
    public double calculateCost(String[] options) {
        carCost += 30000;
        for (String option : options) {
            if (option.equals(Constants.V_8)) {
                carCost += 5500;
            }
        }
        for (String option : options) {
            if (option.equals(Constants.AUTOMATIC)) {
                carCost += 1200;
            }
        }
        for (String option : options) {
            if (option.equals(Constants.PREMIUMAUDIO)) {
                carCost += 1500;
            }
        }
        for (String option : options) {
            if (option.equals(Constants.SUNROOF)) {
                carCost += 1000;
            }
        }
        for (String option : options) {
            if (option.equals(Constants.TOWPACKAGE)) {
                carCost += 500;
            }
        }
        for (String option : options) {
            if (option.equals(Constants.NAVIGATION)) {
                carCost += 1000;
            }
        }
        return carCost;
    }
}
