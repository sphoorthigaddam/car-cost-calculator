package com.dynatrace.CarCostCalculator.carmodels;

public interface Car {
    double calculateCost(String[] options, String destinationZip) throws Exception ;
}
