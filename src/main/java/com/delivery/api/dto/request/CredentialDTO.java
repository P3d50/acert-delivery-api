package com.delivery.api.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CredentialDTO {

    @NotEmpty(message = "{campo.username.obrigatorio}" )
    private String username;
    @NotEmpty(message = "{campo.password.obrigatorio}")
    private String password;
}
