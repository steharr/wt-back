package com.workout.security.infrastructure.repository;

import com.workout.security.infrastructure.repository.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByUsername(String username);
}
