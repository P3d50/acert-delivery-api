package com.delivery.api.dto.request;

import com.delivery.api.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryStatusDTO {

    @NotEmpty(message = "{campo.status.origatorio}")
    private DeliveryStatus status;
}
