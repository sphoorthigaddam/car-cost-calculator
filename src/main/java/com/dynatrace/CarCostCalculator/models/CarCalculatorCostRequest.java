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

    @NotBlank(message = "Type Cannot be Blank/Null")
    private String type;

    private Options selectedOptions;


    private String destinationZip;
}
