package com.dynatrace.CarCostCalculator.service;

import com.dynatrace.CarCostCalculator.carmodels.CarCostCalculatorFactory;
import com.dynatrace.CarCostCalculator.carmodels.Coupe;
import com.dynatrace.CarCostCalculator.carmodels.LuxurySedan;
import com.dynatrace.CarCostCalculator.carmodels.SUV;
import com.dynatrace.CarCostCalculator.exception.OptionNotSupportedException;
import com.dynatrace.CarCostCalculator.models.CarCalculatorCostRequest;
import com.dynatrace.CarCostCalculator.models.Options;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.dynatrace.CarCostCalculator.constants.Constants.AUTOMATIC;
import static com.dynatrace.CarCostCalculator.constants.Constants.SUNROOF;
import static com.dynatrace.CarCostCalculator.constants.Constants.V_8;
import static com.dynatrace.CarCostCalculator.models.VehicleType.COUPE;
import static com.dynatrace.CarCostCalculator.models.VehicleType.LUXURY_SEDAN;
import static com.dynatrace.CarCostCalculator.models.VehicleType.SUV;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CarServiceTest {

    CarService service;

    @MockBean
    CarCostCalculatorFactory factory;

    @BeforeEach
    void setUp() {
        service = new CarService(factory);
    }

    @Test
    void calculateCarCost_ReturnsCarCost_ForTypeCoupe_andValidValues() {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(COUPE.getName())
                .destinationZip("123456")
                .selectedOptions(Options.builder().engine(V_8).navigation(true).transmission(AUTOMATIC).build())
                .build();

        when (factory.getCar(anyString())).thenReturn(new Coupe());

        assertEquals(22000.0, service.calculateCarCost(request).getCost());
        verify(factory).getCar(eq(COUPE.getName()));
    }

    @Test
    void calculateCarCost_throwsException_whenTowPackageIsAnOption() {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(COUPE.getName())
                .destinationZip("123456")
                .selectedOptions(Options.builder().engine(V_8).towPackage(true).transmission(AUTOMATIC).build())
                .build();

        when (factory.getCar(anyString())).thenReturn(new Coupe());

        OptionNotSupportedException ex = assertThrows(OptionNotSupportedException.class, () -> service.calculateCarCost(request));
        assertThat(ex.getMessage().contains("Not available for coupe."));
        verify(factory).getCar(eq(COUPE.getName()));

    }

    @Test
    void calculateCarCost_ReturnsCarCost_ForTypeLuxurySedan_andValidValues() {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(LUXURY_SEDAN.getName())
                .destinationZip("123456")
                .selectedOptions(Options.builder().engine(V_8).navigation(true).roofType(SUNROOF).transmission(AUTOMATIC).build())
                .build();

        when (factory.getCar(anyString())).thenReturn(new LuxurySedan());

        assertEquals(64200.0, service.calculateCarCost(request).getCost());
        verify(factory).getCar(eq(LUXURY_SEDAN.getName()));
    }

    @Test
    void calculateCarCost_ReturnsCarCost_ForTypeSuv_andValidValues() {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(SUV.getName())
                .destinationZip("123456")
                .selectedOptions(Options.builder().engine(V_8).navigation(true).roofType(SUNROOF).transmission(AUTOMATIC).build())
                .build();
        when (factory.getCar(anyString())).thenReturn(new SUV());

        assertEquals(41050.0, service.calculateCarCost(request).getCost());
        verify(factory).getCar(eq(SUV.getName()));
    }
}