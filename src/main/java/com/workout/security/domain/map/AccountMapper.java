package com.workout.security.domain.map;

import com.workout.common.map.Mapper;
import com.workout.security.domain.dto.AccountDetailsBaseDTO;
import com.workout.security.domain.dto.AccountDetailsDTO;
import com.workout.security.domain.model.Account;
import com.workout.security.infrastructure.repository.entity.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper implements Mapper<Account, AccountEntity, AccountDetailsBaseDTO> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private AccountMapper() {
    }

    @Override
    public Account entityToModel(AccountEntity ent) {
        return null;
    }

    @Override
    public AccountEntity modelToEntity(Account model) {
        AccountEntity entity = new AccountEntity();
        entity.setAccountType(model.getAccount().getAccountType());
        entity.setAge(model.getAccount().getAge());
        entity.setGender(model.getAccount().getGender());
        entity.setEmail(model.getAccount().getEmail());
        entity.setPassword(passwordEncoder.encode(model.getAccount().getPassword()));
        entity.setFirstName(model.getAccount().getFirstName());
        entity.setLastName(model.getAccount().getLastName());
        return entity;
    }

    @Override
    public AccountDetailsBaseDTO modelToDto(Account model) {
        AccountDetailsDTO dto = new AccountDetailsDTO();
        dto.setAge(model.getAccount().getAge());
        dto.setGender(model.getAccount().getGender());
        dto.setEmail(model.getAccount().getEmail());
        dto.setFirstName(model.getAccount().getFirstName());
        dto.setLastName(model.getAccount().getLastName());
        dto.setUsername(model.getUsername());
        dto.setAvatarHair(model.getAccount().getAvatar().getHair().getValue());
        dto.setAvatarEyes(model.getAccount().getAvatar().getEyes().getValue());
        return dto;
    }
}