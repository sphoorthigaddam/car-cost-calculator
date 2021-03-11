package com.dynatrace.CarCostCalculator.controller;

import com.dynatrace.CarCostCalculator.models.CarCalculatorCostRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.dynatrace.CarCostCalculator.constants.Constants.AUTOMATIC;
import static com.dynatrace.CarCostCalculator.constants.Constants.COUPE;
import static com.dynatrace.CarCostCalculator.constants.Constants.LUXURY_SEDAN;
import static com.dynatrace.CarCostCalculator.constants.Constants.NAVIGATION;
import static com.dynatrace.CarCostCalculator.constants.Constants.SUNROOF;
import static com.dynatrace.CarCostCalculator.constants.Constants.SUV;
import static com.dynatrace.CarCostCalculator.constants.Constants.TOW_PACKAGE;
import static com.dynatrace.CarCostCalculator.constants.Constants.V_8;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarCostCalculatorControllerTest {
    @Autowired
    CarCostCalculatorController carCostCalculatorController;

    @Test
    void calculateCarCost_ReturnsCarCost_ForTypeCoupe_andValidValues() throws Exception{
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(COUPE)
                .destinationZip("123456")
                .selectedOptions(new String[]{V_8, AUTOMATIC, NAVIGATION})
                .build();

        assertEquals(22000.0, carCostCalculatorController.calculateCarCost(request));
    }

    @Test
    void calculateCarCost_throwsException_whenTowPackageIsAnOption() throws Exception {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(COUPE)
                .destinationZip("123456")
                .selectedOptions(new String[]{V_8, AUTOMATIC, TOW_PACKAGE})
                .build();

        Exception ex = assertThrows(Exception.class, () -> carCostCalculatorController.calculateCarCost(request));
        assertEquals("Not available for coupe.", ex.getMessage());
    }

    @Test
    void calculateCarCost_ReturnsCarCost_ForTypeLuxurySedan_andValidValues() throws Exception{
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(LUXURY_SEDAN)
                .destinationZip("123456")
                .selectedOptions(new String[]{V_8, AUTOMATIC, NAVIGATION, SUNROOF})
                .build();

        assertEquals(64200.0, carCostCalculatorController.calculateCarCost(request));
    }

    @Test
    void calculateCarCost_ReturnsCarCost_ForTypeSuv_andValidValues() throws Exception{
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(SUV)
                .destinationZip("123456")
                .selectedOptions(new String[]{V_8, AUTOMATIC, NAVIGATION, SUNROOF})
                .build();

        assertEquals(41050.0, carCostCalculatorController.calculateCarCost(request));
    }
}