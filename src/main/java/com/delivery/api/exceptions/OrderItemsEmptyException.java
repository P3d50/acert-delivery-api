package com.delivery.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OrderItemsEmptyException extends ResponseStatusException {

    public OrderItemsEmptyException() {
        super(HttpStatus.BAD_REQUEST, "OrderItem list is empty");
    }
}
