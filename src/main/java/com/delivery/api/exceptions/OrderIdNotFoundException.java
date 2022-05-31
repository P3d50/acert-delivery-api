
package com.delivery.api.exceptions;

public class OrderIdNotFoundException extends RuntimeException {

    public OrderIdNotFoundException(String id) {
        super(String.format("Order not found with id %s", id));
    }
}
