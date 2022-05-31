package com.delivery.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ClientNotFoundException extends ResponseStatusException{

    public ClientNotFoundException(String id) {
        super(HttpStatus.BAD_REQUEST, String.format("Client not found with id %s", id));
    }
}
