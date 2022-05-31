package com.delivery.api.dto.request;

import com.delivery.api.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

    @NotEmpty(message = "{campo.phone-type.obrigatorio}")
    private PhoneType type;

    @NotEmpty(message = "{campo.phone-number.obrigatorio}")
    private String number;
}
