package com.delivery.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderInformationItemDTO {

    private String description;
    private BigDecimal price;
    private long quantity;
    private BigDecimal total;

}
