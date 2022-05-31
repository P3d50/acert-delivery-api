package com.delivery.api.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    RECEIVED(),
    ACCEPTED(),
    ORDER_IN_PREPARATION(),
    DONE();
}
