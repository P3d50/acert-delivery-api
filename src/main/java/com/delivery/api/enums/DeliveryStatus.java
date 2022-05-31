package com.delivery.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryStatus {
    ON_DELIVERY(),
    DELIVERED(),
    WAITING(),
    CANCELED();
}
