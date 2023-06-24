package com.workout.security.infrastructure.repository.entity;

import com.workout.security.domain.model.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "ACCOUNTS")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private String email;
    private AccountType accountType;
}
