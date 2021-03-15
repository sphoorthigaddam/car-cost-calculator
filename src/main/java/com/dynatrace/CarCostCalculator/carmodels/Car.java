package com.dynatrace.CarCostCalculator.carmodels;

import com.dynatrace.CarCostCalculator.models.Options;

public interface Car {
    double calculateCost(Options options, String destinationZip);
}
