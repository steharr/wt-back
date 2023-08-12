package com.workout.security.domain.dto;

import com.workout.security.domain.model.AvatarEyesType;
import com.workout.security.domain.model.AvatarHairType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDetailsDTO extends AccountDetailsBaseDTO {
    private String password;


    public AccountDetailsDTO(String firstName, String lastName, Integer age, String gender, String username, String email, AvatarEyesType avatarEyes, AvatarHairType avatarHair, String password) {
        super(firstName, lastName, age, gender, username, email, avatarEyes.getValue(), avatarHair.getValue());
        this.password = password;
    }
}
