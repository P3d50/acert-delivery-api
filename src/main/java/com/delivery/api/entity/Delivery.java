package com.delivery.api.entity;

import com.delivery.api.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {

    private String id;
    private String orderId;
    private String address;
    private DeliveryStatus status;
}
