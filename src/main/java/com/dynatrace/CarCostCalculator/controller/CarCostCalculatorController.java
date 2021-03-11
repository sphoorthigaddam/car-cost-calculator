package com.dynatrace.CarCostCalculator.controller;

import com.dynatrace.CarCostCalculator.models.CarCalculatorCostRequest;
import com.dynatrace.CarCostCalculator.models.CarCostCalculatorResponse;
import com.dynatrace.CarCostCalculator.service.CarCostCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarCostCalculatorController {

    @Autowired
    CarCostCalculatorService service;

    @PostMapping(path = "/car/cost")
    public CarCostCalculatorResponse calculateCarCost(@RequestBody CarCalculatorCostRequest request) throws Exception {
        return CarCostCalculatorResponse.builder().cost(service.calculateCarCost(request)).build();
    }
}
