public class CarCostCalculator_Original {

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

        if (type.equals("coupe")) {
            carCost += 15000;
            for (String option : options) {
                if (option.equals("v8")) {
                    carCost += 5000;
                }
            }
            for (String option : options) {
                if (option.equals("automatic")) {
                    carCost += 1000;
                }
            }
            for (String option : options) {
                if (option.equals("premiumaudio")) {
                    carCost += 1000;
                }
            }
            for (String option : options) {
                if (option.equals("sunroof")) {
                    carCost += 1000;
                }
            }
            for (String option : options) {
                if (option.equals("navigation")) {
                    carCost += 1000;
                }
            }
            for (String option : options) {
                if (option.equals("towpackage")) {
                    throw new Exception("Not available for coupe.");
                }
            }
        }

        if (type.equals("truck")) {
            carCost += 25000;
            for (String option : options) {
                if (option.equals("v8")) {
                    carCost += 6000;
                }
            }
            for (String option : options) {
                if (option.equals("automatic")) {
                    carCost += 1500;
                }
            }
            for (String option : options) {
                if (option.equals("premiumaudio")) {
                    carCost += 1100;
                }
            }
            for (String option : options) {
                if (option.equals("sunroof")) {
                    carCost += 1000;
                }
            }
            for (String option : options) {
                if (option.equals("towpackage")) {
                    carCost += 550;
                }
            }
            for (String option : options) {
                if (option.equals("navigation")) {
                    carCost += 1000;
                }
            }
        }

        if (type.equals("suv")) {
            carCost += 30000;
            for (String option : options) {
                if (option.equals("v8")) {
                    carCost += 5500;
                }
            }
            for (String option : options) {
                if (option.equals("automatic")) {
                    carCost += 1200;
                }
            }
            for (String option : options) {
                if (option.equals("premiumaudio")) {
                    carCost += 1500;
                }
            }
            for (String option : options) {
                if (option.equals("sunroof")) {
                    carCost += 1000;
                }
            }
            for (String option : options) {
                if (option.equals("towpackage")) {
                    carCost += 500;
                }
            }
            for (String option : options) {
                if (option.equals("navigation")) {
                    carCost += 1000;
                }
            }
        }

        if (type.equals("luxury_sedan")) {
            carCost += 35000;
            for (String option : options) {
                if (option.equals("v8")) {
                    carCost += 25000;
                }
            }
            for (String option : options) {
                if (option.equals("automatic")) {
                    carCost += 1200;
                }
            }
            for (String option : options) {
                if (option.equals("premiumaudio")) {
                    carCost += 1500;
                }
            }
            for (String option : options) {
                if (option.equals("sunroof")) {
                    carCost += 1000;
                }
            }
            for (String option : options) {
                if (option.equals("navigation")) {
                    carCost += 1000;
                }
            }
            for (String option : options) {
                if (option.equals("towpackage")) {
                    carCost += 500;
                }
            }

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
