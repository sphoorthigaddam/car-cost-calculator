package com.dynatrace.CarCostCalculator.carmodels;

public class Truck {

    public static final String V_8 = "v8";
    public static final String AUTOMATIC = "automatic";
    public static final String PREMIUMAUDIO = "premiumaudio";
    public static final String SUNROOF = "sunroof";
    public static final String TOWPACKAGE = "towpackage";
    public static final String NAVIGATION = "navigation";
    double carCost = 0.0;
    public double calculateCost(String[] options) {
        carCost += 25000;
        for (String option : options) {
            if (option.equals(V_8)) {
                carCost += 6000;
            }
        }
        for (String option : options) {
            if (option.equals(AUTOMATIC)) {
                carCost += 1500;
            }
        }
        for (String option : options) {
            if (option.equals(PREMIUMAUDIO)) {
                carCost += 1100;
            }
        }
        for (String option : options) {
            if (option.equals(SUNROOF)) {
                carCost += 1000;
            }
        }
        for (String option : options) {
            if (option.equals(TOWPACKAGE)) {
                carCost += 550;
            }
        }
        for (String option : options) {
            if (option.equals(NAVIGATION)) {
                carCost += 1000;
            }
        }
        return carCost;
    }
}
