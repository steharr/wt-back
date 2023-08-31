package com.workout.security.domain.model;

import com.workout.common.exception.ApplicationException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum AvatarEyesType {
    CONFIDENT("confident"),
    HAPPY("happy"),
    NORMAL("normal");

    private final String value;

    AvatarEyesType(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public static AvatarEyesType getEnumFromValue(String value) {
        return Arrays.stream(AvatarEyesType.values()).filter(e -> e.value.equals(value)).findFirst()
                .orElseThrow(() -> new ApplicationException("Invalid Enum!"));
    }

    public static List<String> choices() {
        return Arrays.stream(AvatarEyesType.values()).map(e -> e.value).collect(Collectors.toList());
    }
}
