package com.dynatrace.CarCostCalculator.controller;

import com.dynatrace.CarCostCalculator.models.CalculateCarCostRequest;
import com.dynatrace.CarCostCalculator.service.CarCostCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarCostCalculatorController {

    @Autowired
    CarCostCalculatorService service;

    @PostMapping
    public double calculateCarCost(@RequestBody CalculateCarCostRequest request) throws Exception {
        return service.calculateCarCost(request);
    }
}
