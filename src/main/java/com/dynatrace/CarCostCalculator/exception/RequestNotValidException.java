package com.dynatrace.CarCostCalculator.exception;

public class RequestNotValidException extends RuntimeException {
    public RequestNotValidException(String message) {
        super(message);
    }
}
