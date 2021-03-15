package com.dynatrace.CarCostCalculator.controller;

import com.dynatrace.CarCostCalculator.exception.OptionNotSupportedException;
import com.dynatrace.CarCostCalculator.models.CarCalculatorCostRequest;
import com.dynatrace.CarCostCalculator.models.Options;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.dynatrace.CarCostCalculator.constants.Constants.AUTOMATIC;
import static com.dynatrace.CarCostCalculator.constants.Constants.SUNROOF;
import static com.dynatrace.CarCostCalculator.constants.Constants.V_8;
import static com.dynatrace.CarCostCalculator.models.VehicleType.COUPE;
import static com.dynatrace.CarCostCalculator.models.VehicleType.LUXURY_SEDAN;
import static com.dynatrace.CarCostCalculator.models.VehicleType.SUV;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CarControllerTest {
    @Autowired
    CarController carController;

    @Test
    void calculateCarCost_ReturnsCarCost_ForTypeCoupe_andValidValues() {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(COUPE.getName())
                .destinationZip("123456")
                .selectedOptions(Options.builder().engine(V_8).navigation(true).transmission(AUTOMATIC).build())
                .build();

        assertEquals(22000.0, carController.calculateCarCostV2(request).getBody().getCost());
    }

    @Test
    void calculateCarCost_throwsException_whenTowPackageIsAnOption() {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(COUPE.getName())
                .destinationZip("123456")
                .selectedOptions(Options.builder().engine(V_8).towPackage(true).transmission(AUTOMATIC).build())
                .build();

        OptionNotSupportedException ex = assertThrows(OptionNotSupportedException.class, () -> carController.calculateCarCostV2(request));
        assertThat(ex.getMessage().contains("Not available for coupe."));

    }

    @Test
    void calculateCarCost_ReturnsCarCost_ForTypeLuxurySedan_andValidValues() {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(LUXURY_SEDAN.getName())
                .destinationZip("123456")
                .selectedOptions(Options.builder().engine(V_8).navigation(true).roofType(SUNROOF).transmission(AUTOMATIC).build())
                .build();

        assertEquals(64200.0, carController.calculateCarCostV2(request).getBody().getCost());
    }

    @Test
    void calculateCarCost_ReturnsCarCost_ForTypeSuv_andValidValues() {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(SUV.getName())
                .destinationZip("123456")
                .selectedOptions(Options.builder().engine(V_8).navigation(true).roofType(SUNROOF).transmission(AUTOMATIC).build())
                .build();

        assertEquals(41050.0, carController.calculateCarCostV2(request).getBody().getCost());
    }
}