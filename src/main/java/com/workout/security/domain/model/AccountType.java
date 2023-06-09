package com.workout.security.domain.model;

import java.util.Arrays;
import java.util.Optional;

public enum AccountType {
    USER(1), ADMIN(2);

    private Integer code;

    public Integer getCode() {
        return code;
    }

    AccountType(Integer code) {
        this.code = code;
    }

    public static AccountType getTypeByCode(Integer code) throws NoSuchFieldException {
        Optional<AccountType> first = Arrays.stream(AccountType.values()).filter(elem -> elem.code.equals(code)).findFirst();
        if (first.isPresent()) {
            return first.get();
        }
        throw new NoSuchFieldException("");
    }
}
