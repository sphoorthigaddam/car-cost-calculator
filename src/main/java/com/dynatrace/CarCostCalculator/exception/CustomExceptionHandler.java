package com.dynatrace.CarCostCalculator.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleException(Exception ex) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),
                internalServerError.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(errorMessage, internalServerError);
    }

    @ExceptionHandler(OptionNotSupportedException.class)
    public final ResponseEntity<ErrorMessage> handleException(OptionNotSupportedException ex) {
        HttpStatus notFound = HttpStatus.BAD_REQUEST;

        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),
                notFound.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(errorMessage, notFound);
    }


    @ExceptionHandler(RequestNotValidException.class)
    public final ResponseEntity<ErrorMessage> handleException(RequestNotValidException ex) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),
                badRequest.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(errorMessage, badRequest);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(ex.getMessage());

        ErrorMessage errorMessage = new ErrorMessage(message,
                badRequest.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
