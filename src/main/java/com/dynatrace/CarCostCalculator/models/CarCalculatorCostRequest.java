package com.dynatrace.CarCostCalculator.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarCalculatorCostRequest {

    @NotBlank(message = "Car type Cannot be blank/null")
    private String type;

    private Options selectedOptions;


    private String destinationZip;
}
