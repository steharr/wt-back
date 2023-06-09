package com.workout.security.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDetailsDTO extends AccountDetailsBaseDTO {
    private String password;

    public AccountDetailsDTO(String firstName, String lastName, Integer age, String gender, String username, String email, String password) {
        super(firstName, lastName, age, gender, username, email);
        this.password = password;
    }
}
