package com.dynatrace.CarCostCalculator.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Options {
    public String engine;
    public String transmission;
    public String audio;
    public String roofType;
    public boolean towPackage;
    public boolean navigation;
}
