package com.delivery.api.dto.request;

import com.delivery.api.entity.Phone;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private String id;

    private String lastName;

    @NotEmpty(message = "{campo.nome.firstname.obrigatorio}")
    private String firstName;

    @NotEmpty(message = "{campo.username.obrigatorio}")
    private String username;

    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String CPF;

    @NotEmpty(message = "{campo.phones.obrigatorio}")
    private List<Phone> phones;

    @NotEmpty(message = "{campo.email.obrigatorio}")
    private String email;

}
