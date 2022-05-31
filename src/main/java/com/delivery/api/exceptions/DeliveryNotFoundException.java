package com.delivery.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DeliveryNotFoundException extends ResponseStatusException {

    public DeliveryNotFoundException(String id) {
        super(HttpStatus.BAD_REQUEST, String.format("Delivery not found with id %s", id));
    }
}
