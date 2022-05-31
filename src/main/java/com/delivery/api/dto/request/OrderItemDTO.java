package com.delivery.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String description;
    @NotEmpty(message = "{campo.preco.obrigatorio}")
    private BigDecimal price;
    @NotEmpty(message = "{campo.quantidade.origatorio}")
    private long quantity;
}
