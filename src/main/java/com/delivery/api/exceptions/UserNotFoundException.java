
package com.delivery.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {

    public UserNotFoundException(String username) {
        super(HttpStatus.NOT_FOUND, String.format("User not found with username %s", username));
    }
}
