package com.delivery.api.dto.request;

import com.delivery.api.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusDTO {

    @NotEmpty(message = "{campo.status.origatorio}")
    private OrderStatus status;
}
