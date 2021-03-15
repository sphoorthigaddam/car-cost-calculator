package com.dynatrace.CarCostCalculator.controller;

import com.dynatrace.CarCostCalculator.models.CarCalculatorCostRequest;
import com.dynatrace.CarCostCalculator.models.CarCostCalculatorResponse;
import com.dynatrace.CarCostCalculator.service.CarCostCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
public class CarCostCalculatorController {

    private final CarCostCalculatorService service;

    @Autowired
    public CarCostCalculatorController(CarCostCalculatorService service) {
        this.service = service;
    }

    @PostMapping(path = "api/v1/car/cost",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarCostCalculatorResponse> calculateCarCostV2(@Valid @RequestBody CarCalculatorCostRequest request) {
        return ResponseEntity.ok().body(service.calculateCarCost(request));
    }
}
