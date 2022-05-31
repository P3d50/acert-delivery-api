package com.delivery.api.entity;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String id;
    private String lastName;
    @NotEmpty(  message = "Field name could not to be empty")
    private String firstName;
    private String username;
    private String CPF;
    private List<Phone> phones;
    private String email;

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", username='" + username + '\'' +
                ", CPF='" + CPF + '\'' +
                ", phones=" + phones +
                ", email='" + email + '\'' +
                '}';
    }
}
