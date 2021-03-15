package com.dynatrace.CarCostCalculator.exception;

public class OptionNotSupportedException extends RuntimeException {
    public OptionNotSupportedException(String message) {
        super(message);
    }
}
