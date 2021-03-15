package com.dynatrace.CarCostCalculator;

import com.dynatrace.CarCostCalculator.carmodels.Car;
import com.dynatrace.CarCostCalculator.carmodels.CarCostCalculatorFactory;
import com.dynatrace.CarCostCalculator.models.Options;
import com.dynatrace.CarCostCalculator.models.VehicleType;

public class CarCostCalculator {

    public static void main(String[] args) {
        try {
            Options options = Options.builder().engine("v8").transmission("automatic").navigation(true).build();
            long startTime = System.currentTimeMillis();
            double carCost = calculateCarCost("coupe", options, "12345");
            long deltaTime = System.currentTimeMillis() - startTime;
            System.out.println("Car costs: $" + carCost + " calculation took " + deltaTime / 1000 + " seconds");


            Options options2 = Options.builder().engine("v8").transmission("automatic").navigation(true).roofType("sunroof").build();
            startTime = System.currentTimeMillis();
            carCost = calculateCarCost("luxury_sedan", options2, "12345");
            deltaTime = System.currentTimeMillis() - startTime;
            System.out.println("Car costs: $" + carCost + " calculation took " + deltaTime / 1000 + " seconds");

            Options options3 = Options.builder().engine("v8").transmission("automatic").navigation(true).build();
            startTime = System.currentTimeMillis();
            carCost = calculateCarCost("luxury_sedan", options3, "12345");
            deltaTime = System.currentTimeMillis() - startTime;
            System.out.println("Car costs: $" + carCost + " calculation took " + deltaTime / 1000 + " seconds");

            Options options4 = Options.builder().engine("v8").transmission("automatic").navigation(true).roofType("sunroof").build();
            startTime = System.currentTimeMillis();
            carCost = calculateCarCost("suv", options4, "12345");
            deltaTime = System.currentTimeMillis() - startTime;
            System.out.println("Car costs: $" + carCost + " calculation took " + deltaTime / 1000 + " seconds");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double calculateCarCost(String type, Options selectedOptions, String destinationZip) {
        Car car = VehicleType.getVehicleType(type).getType();

        double carCost = car.calculateCost(selectedOptions, destinationZip);

        if (carCost > 30000 && carCost < 60000) {
            // luxury tax
            carCost += 850;
        } else if (carCost > 60000) {
            // extra luxury tax
            carCost += 1000;
        }

        return carCost;
    }
}
