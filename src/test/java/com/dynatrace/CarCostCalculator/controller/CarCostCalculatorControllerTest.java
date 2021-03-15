package com.dynatrace.CarCostCalculator.controller;

import com.dynatrace.CarCostCalculator.exception.OptionNotSupportedException;
import com.dynatrace.CarCostCalculator.models.CarCalculatorCostRequest;
import com.dynatrace.CarCostCalculator.models.Options;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.dynatrace.CarCostCalculator.constants.Constants.AUTOMATIC;
import static com.dynatrace.CarCostCalculator.constants.Constants.COUPE;
import static com.dynatrace.CarCostCalculator.constants.Constants.LUXURY_SEDAN;
import static com.dynatrace.CarCostCalculator.constants.Constants.SUNROOF;
import static com.dynatrace.CarCostCalculator.constants.Constants.SUV;
import static com.dynatrace.CarCostCalculator.constants.Constants.V_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CarCostCalculatorControllerTest {
    @Autowired
    CarCostCalculatorController carCostCalculatorController;

    @Test
    void calculateCarCost_ReturnsCarCost_ForTypeCoupe_andValidValues() {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(COUPE)
                .destinationZip("123456")
                .selectedOptions(Options.builder().engine(V_8).navigation(true).transmission(AUTOMATIC).build())
                .build();

        assertEquals(22000.0, carCostCalculatorController.calculateCarCostV2(request).getBody().getCost());
    }

    @Test
    void calculateCarCost_throwsException_whenTowPackageIsAnOption() {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(COUPE)
                .destinationZip("123456")
                .selectedOptions(Options.builder().engine(V_8).towPackage(true).transmission(AUTOMATIC).build())
                .build();

        OptionNotSupportedException ex = assertThrows(OptionNotSupportedException.class, () -> carCostCalculatorController.calculateCarCostV2(request));
        assertThat(ex.getMessage().contains("Not available for coupe."));

    }

    @Test
    void calculateCarCost_ReturnsCarCost_ForTypeLuxurySedan_andValidValues() {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(LUXURY_SEDAN)
                .destinationZip("123456")
                .selectedOptions(Options.builder().engine(V_8).navigation(true).roofType(SUNROOF).transmission(AUTOMATIC).build())
                .build();

        assertEquals(64200.0, carCostCalculatorController.calculateCarCostV2(request).getBody().getCost());
    }

    @Test
    void calculateCarCost_ReturnsCarCost_ForTypeSuv_andValidValues() {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(SUV)
                .destinationZip("123456")
                .selectedOptions(Options.builder().engine(V_8).navigation(true).roofType(SUNROOF).transmission(AUTOMATIC).build())
                .build();

        assertEquals(41050.0, carCostCalculatorController.calculateCarCostV2(request).getBody().getCost());
    }
}