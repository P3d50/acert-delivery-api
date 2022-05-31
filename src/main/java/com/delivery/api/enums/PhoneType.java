package com.delivery.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum PhoneType {

    COMERCIAL_PHONE("Comercial"),
    MOBILE_PHONE("Moblie"),
    HOME_PHONE("Home");

    private final String description;
}
