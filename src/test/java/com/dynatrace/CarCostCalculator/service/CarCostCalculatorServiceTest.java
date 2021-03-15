package com.dynatrace.CarCostCalculator.service;

import com.dynatrace.CarCostCalculator.carmodels.Coupe;
import com.dynatrace.CarCostCalculator.carmodels.LuxurySedan;
import com.dynatrace.CarCostCalculator.carmodels.SUV;
import com.dynatrace.CarCostCalculator.exception.OptionNotSupportedException;
import com.dynatrace.CarCostCalculator.models.CarCalculatorCostRequest;
import com.dynatrace.CarCostCalculator.models.Options;
import com.dynatrace.CarCostCalculator.models.VehicleType;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CarCostCalculatorServiceTest {

    @Autowired
    CarCostCalculatorService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void calculateCarCost_ReturnsCarCost_ForTypeCoupe_andValidValues() {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(COUPE)
                .destinationZip("123456")
                .selectedOptions(Options.builder().engine(V_8).navigation(true).transmission(AUTOMATIC).build())
                .build();

        when (VehicleType.getVehicleType(anyString()).getType()).thenReturn(new Coupe());

        assertEquals(22000.0, service.calculateCarCost(request).getCost());
        verify(VehicleType.getVehicleType(eq(COUPE)));
    }

    @Test
    void calculateCarCost_throwsException_whenTowPackageIsAnOption() {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(COUPE)
                .destinationZip("123456")
                .selectedOptions(Options.builder().engine(V_8).towPackage(true).transmission(AUTOMATIC).build())
                .build();

        when (VehicleType.getVehicleType(anyString()).getType()).thenReturn(new Coupe());

        OptionNotSupportedException ex = assertThrows(OptionNotSupportedException.class, () -> service.calculateCarCost(request));
        assertThat(ex.getMessage().contains("Not available for coupe."));
        verify(VehicleType.getVehicleType(eq(COUPE)));
    }

    @Test
    void calculateCarCost_ReturnsCarCost_ForTypeLuxurySedan_andValidValues() {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(LUXURY_SEDAN)
                .destinationZip("123456")
                .selectedOptions(Options.builder().engine(V_8).navigation(true).roofType(SUNROOF).transmission(AUTOMATIC).build())
                .build();

        when (VehicleType.getVehicleType(anyString()).getType()).thenReturn(new LuxurySedan());

        assertEquals(64200.0, service.calculateCarCost(request).getCost());
        verify(VehicleType.getVehicleType(eq(LUXURY_SEDAN)));
    }

    @Test
    void calculateCarCost_ReturnsCarCost_ForTypeSuv_andValidValues() {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(SUV)
                .destinationZip("123456")
                .selectedOptions(Options.builder().engine(V_8).navigation(true).roofType(SUNROOF).transmission(AUTOMATIC).build())
                .build();
        when (VehicleType.getVehicleType(anyString()).getType()).thenReturn(new SUV());

        assertEquals(41050.0, service.calculateCarCost(request).getCost());
        verify(VehicleType.getVehicleType(eq(SUV)));
    }
}