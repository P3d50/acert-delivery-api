package com.delivery.api.dto.request;

import com.delivery.api.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String id;
    private OrderStatus status;
    @NotEmpty(message = "{campo.codigo-cliente.obrigatorio}")
    private String clientId;
    @NotEmpty(message = "{campo.items-pedido.obrigatorio}")
    private List<OrderItemDTO> itemList;
    private String date;
}
