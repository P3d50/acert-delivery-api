package com.delivery.api.entity;

import com.delivery.api.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    private PhoneType type;

    private String number;

}
