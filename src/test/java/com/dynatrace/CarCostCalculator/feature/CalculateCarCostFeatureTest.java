package com.dynatrace.CarCostCalculator.feature;

import com.dynatrace.CarCostCalculator.controller.CarCostCalculatorController;
import com.dynatrace.CarCostCalculator.exception.CustomExceptionHandler;
import com.dynatrace.CarCostCalculator.models.CarCalculatorCostRequest;
import com.dynatrace.CarCostCalculator.models.Options;
import com.dynatrace.CarCostCalculator.service.CarCostCalculatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.dynatrace.CarCostCalculator.constants.Constants.AUTOMATIC;
import static com.dynatrace.CarCostCalculator.constants.Constants.COUPE;
import static com.dynatrace.CarCostCalculator.constants.Constants.LUXURY_SEDAN;
import static com.dynatrace.CarCostCalculator.constants.Constants.PREMIUM_AUDIO;
import static com.dynatrace.CarCostCalculator.constants.Constants.SUNROOF;
import static com.dynatrace.CarCostCalculator.constants.Constants.SUV;
import static com.dynatrace.CarCostCalculator.constants.Constants.TRUCK;
import static com.dynatrace.CarCostCalculator.constants.Constants.V_8;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculateCarCostFeatureTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    CarCostCalculatorService service;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new CarCostCalculatorController(service))
                .setControllerAdvice(new CustomExceptionHandler())
                .build();
    }

    @Test
    public void calculateCarCost_ReturnsCarCost_whenValidRequestForCoupeIsPassed() throws Exception {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(COUPE)
                .destinationZip("123456")
                .selectedOptions(Options.builder()
                        .engine(V_8)
                        .navigation(true)
                        .transmission(AUTOMATIC)
                        .build())
                .build();

        mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/car/cost")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cost", is(22000.0)));
    }

    @Test
    public void calculateCarCost_throwsException_whenInvalidOptionForCoupeIsPassed() throws Exception {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(COUPE)
                .destinationZip("123456")
                .selectedOptions(Options.builder()
                        .engine(V_8)
                        .navigation(true)
                        .transmission(AUTOMATIC)
                        .towPackage(true)
                        .build())
                .build();

        mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/car/cost")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("option not available for coupe.")));
    }

    @Test
    public void calculateCarCost_throwsException_whenCarTypeIsInvalid() throws Exception {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type("Electric")
                .destinationZip("123456")
                .selectedOptions(Options.builder()
                        .engine(V_8)
                        .navigation(true)
                        .transmission(AUTOMATIC)
                        .towPackage(true)
                        .build())
                .build();

        mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/car/cost")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("car type not available at the moment!")));
    }

    @Test
    public void calculateCarCost_returnsCarCost_withValidRequestAndNoOptions() throws Exception {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(TRUCK)
                .destinationZip("123456")
                .build();

        mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/car/cost")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cost", is(26500.0)));
    }

    @Test
    public void calculateCarCost_returnsCarCost_withValidRequestForSUV() throws Exception {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(SUV)
                .destinationZip("123456")
                .selectedOptions(Options.builder()
                        .engine(V_8)
                        .navigation(true)
                        .transmission(AUTOMATIC)
                        .towPackage(true)
                        .roofType(SUNROOF)
                        .audio(PREMIUM_AUDIO)
                        .build())
                .build();

        mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/car/cost")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cost", is(43050.0)));
    }

    @Test
    public void calculateCarCost_returnsCarCost_withValidRequestForLuxurySedan() throws Exception {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .type(LUXURY_SEDAN)
                .destinationZip("123456")
                .selectedOptions(Options.builder()
                        .engine(V_8)
                        .navigation(true)
                        .transmission(AUTOMATIC)
                        .audio(PREMIUM_AUDIO)
                        .build())
                .build();

        mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/car/cost")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cost", is(64700.0)));
    }

    @Test
    public void calculateCarCost_throwsException_withMissingRequest() throws Exception {
        CarCalculatorCostRequest request = CarCalculatorCostRequest.builder()
                .destinationZip("123456")
                .selectedOptions(Options.builder()
                        .engine(V_8)
                        .navigation(true)
                        .transmission(AUTOMATIC)
                        .audio(PREMIUM_AUDIO)
                        .build())
                .build();

        mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/car/cost")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("Type Cannot be Blank/Null")));

    }
}
