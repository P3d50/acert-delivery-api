package com.delivery.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AppUserDTO {
    private String id;
    @NotEmpty(message = "{campo.username.obrigatorio}")
    private String username;
    @NotEmpty(message = "{campo.password.obrigatorio}")
    private String password;
}
