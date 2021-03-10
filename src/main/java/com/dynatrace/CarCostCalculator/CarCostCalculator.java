package com.dynatrace.CarCostCalculator;

import com.dynatrace.CarCostCalculator.carmodels.Car;

public class CarCostCalculator {

    public static void main(String[] args) {
        try {
            String[] options = {"v8", "automatic", "navigation"};
            long startTime = System.currentTimeMillis();
            double carCost = calculateCarCost("coupe", options, "12345");
            long deltaTime = System.currentTimeMillis() - startTime;
            System.out.println("Car costs: $" + carCost + " calculation took " + deltaTime / 1000 + " seconds");

            String[] options2 = {"v8", "automatic", "navigation", "sunroof"};
            startTime = System.currentTimeMillis();
            carCost = calculateCarCost("luxury_sedan", options2, "12345");
            deltaTime = System.currentTimeMillis() - startTime;
            System.out.println("Car costs: $" + carCost + " calculation took " + deltaTime / 1000 + " seconds");

            String[] options3 = {"v8", "automatic", "navigation"};
            startTime = System.currentTimeMillis();
            carCost = calculateCarCost("luxury_sedan", options3, "12345");
            deltaTime = System.currentTimeMillis() - startTime;
            System.out.println("Car costs: $" + carCost + " calculation took " + deltaTime / 1000 + " seconds");

            String[] options4 = {"v8", "automatic", "navigation", "sunroof"};
            startTime = System.currentTimeMillis();
            carCost = calculateCarCost("suv", options4, "12345");
            deltaTime = System.currentTimeMillis() - startTime;
            System.out.println("Car costs: $" + carCost + " calculation took " + deltaTime / 1000 + " seconds");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double calculateCarCost(String type, String[] selectedOptions, String destinationZip) throws Exception {
        CarCostCalculatorFactory factory = new CarCostCalculatorFactory();
        Car car = factory.getCar(type);
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
