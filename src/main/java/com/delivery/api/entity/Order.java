package com.delivery.api.entity;

import com.delivery.api.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private OrderStatus status;
    private String clientId;
    private List<OrderItem> itemList;
    private String date;

}
