package com.workout.security.domain.model;

import com.workout.common.exception.ApplicationException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum AvatarHairType {

    BALDNESS("balndess"),
    CLASSIC_1("classic01"),
    CLASSIC_2("classic02"),
    CURLY("curly"),
    ELVIS("elvis"),
    LONG("long"),
    PONYTAIL("ponyTail"),
    SLAUGHTER("slaughter"),
    STYLISH("stylish");

    private final String value;

    AvatarHairType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static AvatarHairType getEnumFromValue(String value) {
        return Arrays.stream(AvatarHairType.values()).filter(e -> e.value.equals(value)).findFirst()
                .orElseThrow(() -> new ApplicationException("Invalid Enum!"));
    }

    public static List<String> choices() {
        return Arrays.stream(AvatarHairType.values()).map(e -> e.value).collect(Collectors.toList());
    }
}
