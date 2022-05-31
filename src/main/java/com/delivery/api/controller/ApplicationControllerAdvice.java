package com.delivery.api.controller;

import com.delivery.api.exceptions.ClientNotFoundException;
import com.delivery.api.exceptions.DeliveryNotFoundException;
import com.delivery.api.exceptions.OrderIdNotFoundException;
import com.delivery.api.exceptions.OrderNotFoundException;
import com.delivery.api.exceptions.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(OrderNotFoundException.class)
    public ApiErrors handleOrderNotFoundException(OrderNotFoundException exception) {
        return new ApiErrors(exception.getMessage());
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ApiErrors handleClientNotFoundExcpetion(ClientNotFoundException exception) {
        return new ApiErrors(exception.getMessage());
    }

    @ExceptionHandler(DeliveryNotFoundException.class)
    public ApiErrors handleDeliveryNotFoundExcpetion(DeliveryNotFoundException exception) {
        return new ApiErrors(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderIdNotFoundException.class)
    public ApiErrors handleOrderIdNotFoundExceprion(OrderIdNotFoundException orderIdNotFoundException) {
        return new ApiErrors(orderIdNotFoundException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrors HandleFieldNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
       List<String> errors =methodArgumentNotValidException.getBindingResult()
                .getAllErrors()
                .stream().map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErrors(errors);
    }
}
