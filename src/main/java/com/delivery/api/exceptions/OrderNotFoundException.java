
package com.delivery.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OrderNotFoundException extends ResponseStatusException {

    public OrderNotFoundException(String id) {
        super(HttpStatus.BAD_REQUEST, String.format("Order not found with id %s", id));
    }
}
