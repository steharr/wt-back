package com.workout.security.infrastructure.repository.entity;

import com.workout.security.domain.model.AvatarEyesType;
import com.workout.security.domain.model.AvatarHairType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "AVATARS")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AvatarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long avatar_id;
    @Enumerated(EnumType.STRING)
    private AvatarEyesType eyes;
    @Enumerated(EnumType.STRING)
    private AvatarHairType hair;


}
