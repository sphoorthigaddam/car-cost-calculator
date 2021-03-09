package com.dynatrace.CarCostCalculator;

import com.dynatrace.CarCostCalculator.carmodels.Coupe;
import com.dynatrace.CarCostCalculator.carmodels.LuxurySedan;
import com.dynatrace.CarCostCalculator.carmodels.SUV;
import com.dynatrace.CarCostCalculator.carmodels.Truck;

public class CarCostCalculator {

    public static final String COUPE = "coupe";
    public static final String TRUCK = "truck";
    public static final String SUV = "suv";
    public static final String LUXURY_SEDAN = "luxury_sedan";

    public static void main(String[] args) {
        try {
            String[] options = { "v8", "automatic", "navigation" };
            long startTime = System.currentTimeMillis();
            double carCost = calculateCarCost("coupe", options, "12345");
            long deltaTime = System.currentTimeMillis() - startTime;
            System.out.println("Car costs: $" + carCost + " calculation took " + deltaTime / 1000 + " seconds");

            String[] options2 = { "v8", "automatic", "navigation", "sunroof" };
            startTime = System.currentTimeMillis();
            carCost = calculateCarCost("luxury_sedan", options2, "12345");
            deltaTime = System.currentTimeMillis() - startTime;
            System.out.println("Car costs: $" + carCost + " calculation took " + deltaTime / 1000 + " seconds");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double calculateCarCost(String type, String[] options, String destinationZip) throws Exception {
        double carCost = 0.0;

        if (type.equals(COUPE)) {
            Coupe coupe = new Coupe();
            carCost = coupe.calculateCost(options);
        }

        if (type.equals(TRUCK)) {
            Truck truck = new Truck();
            carCost = truck.calculateCost(options);
        }

        if (type.equals(SUV)) {
           com.dynatrace.CarCostCalculator.carmodels.SUV suv = new SUV();
            carCost = suv.calculateCost(options);
        }

        if (type.equals(LUXURY_SEDAN)) {
            LuxurySedan luxurySedan = new LuxurySedan();
            carCost = luxurySedan.calculateCost(options);

        }

        if (carCost > 30000 && carCost < 60000) {
            // luxury tax
            carCost += 850;
        } else if (carCost > 60000) {
            // extra luxury tax
            carCost += 1000;
        }

        String[] gasGuzzlers = { "truck", "suv" };
        double tax = 0;
        for (String gasGuzzler : gasGuzzlers) {
            tax = slowTaxCalculationMethod(destinationZip);
            if (gasGuzzler.equals(type)) {
                // adding gas guzzler tax
                tax += 1000;
            }
        }
        carCost += tax;

        return carCost;
    }

    private static double slowTaxCalculationMethod(String destinationZip) {
        // the Thread.sleep cannot be removed
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
            // Do nothing
        }
        return 500;
    }

}
