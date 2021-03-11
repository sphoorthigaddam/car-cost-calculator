package com.dynatrace.CarCostCalculator.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarCalculatorCostRequest {
    public String type;
    public String[] selectedOptions;
    public String destinationZip;
}
