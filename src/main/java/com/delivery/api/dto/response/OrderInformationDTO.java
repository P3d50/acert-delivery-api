package com.delivery.api.dto.response;

import com.delivery.api.dto.request.DeliveryDTO;
import com.delivery.api.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderInformationDTO {
    private String codigo;
    private String CPF;
    private String clientName;
    private BigDecimal total;
    private List<OrderInformationItemDTO> items;
    private List<DeliveryDTO> deliverys;
    private OrderStatus status;
}
