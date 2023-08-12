package com.workout.security.application;

import com.workout.security.domain.dto.AccountDetailsBaseDTO;
import com.workout.security.domain.dto.AccountDetailsDTO;
import com.workout.security.domain.map.AccountMapper;
import com.workout.security.domain.model.Account;
import com.workout.security.domain.model.AvatarEyesType;
import com.workout.security.domain.model.AvatarHairType;
import com.workout.security.infrastructure.repository.AccountRepository;
import com.workout.security.infrastructure.repository.AvatarRepository;
import com.workout.security.infrastructure.repository.entity.AccountEntity;
import com.workout.security.infrastructure.repository.entity.AvatarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AvatarRepository avatarRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new Account(accountRepository.findByUsername(username).orElseThrow());
    }

    public AccountDetailsBaseDTO loadUserDetailsByAuth(Authentication a) throws UsernameNotFoundException {
        return mapper.modelToDto(new Account(accountRepository.findByUsername((String) a.getPrincipal()).orElseThrow()));
    }

    public AccountEntity loadUserEntityByUsername(String username) throws UsernameNotFoundException {
        return new Account(accountRepository.findByUsername(username).orElseThrow()).getAccount();
    }

    public AccountEntity save(AccountDetailsDTO account) {
        AccountEntity entity = this.transformDTOToEntity(account);
        accountRepository.saveAndFlush(entity);
        return entity;
    }

    private AccountEntity transformDTOToEntity(AccountDetailsDTO dto) {
        AccountEntity entity = new AccountEntity();
        entity.setAccountType(null);
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setUsername(dto.getUsername());
        if (hasAvatar(dto)) {
            AvatarEntity avatarEntity = new AvatarEntity();
            avatarEntity.setHair(AvatarHairType.getEnumFromValue(dto.getAvatarHair()));
            avatarEntity.setEyes(AvatarEyesType.getEnumFromValue(dto.getAvatarEyes()));
            entity.setAvatar(avatarEntity);
        }
        return entity;
    }

    private boolean hasAvatar(AccountDetailsDTO dto) {
        return null != dto.getAvatarEyes() && null != dto.getAvatarHair();
    }


}
