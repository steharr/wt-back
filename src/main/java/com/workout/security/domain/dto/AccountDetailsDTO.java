package com.workout.security.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsDTO {
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private String username;
    private String password;
    private String email;
}
