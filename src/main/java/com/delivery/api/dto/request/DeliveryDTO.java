package com.delivery.api.dto.request;

import com.delivery.api.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDTO {
    private String id;
    @NotEmpty(message = "{campo.codigo-pedido.obrigatorio}")
    private String orderId;
    @NotEmpty(message = "{campo.address.origatorio}")
    private String address;
    private DeliveryStatus status;
}
