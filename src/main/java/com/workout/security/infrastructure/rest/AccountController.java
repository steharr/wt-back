package com.workout.security.infrastructure.rest;

import com.workout.security.application.config.AuthProvider;
import com.workout.security.application.config.JwtUtil;
import com.workout.security.domain.dto.AccountDetailsDTO;
import com.workout.security.domain.dto.AccountLoginDTO;
import com.workout.security.domain.dto.JwtTokenDTO;
import com.workout.security.domain.model.AccountService;
import com.workout.security.infrastructure.repository.entity.AccountEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AuthProvider authProvider;
    @Autowired
    private AccountService accountService;

    @PostMapping("auth")
    public ResponseEntity<JwtTokenDTO> authenticate(@RequestBody AccountLoginDTO login) {
        try {
            Authentication authentication = authProvider
                    .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = JwtUtil.generateToken(authentication);
            return ResponseEntity.ok(new JwtTokenDTO(token));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("register")
    public ResponseEntity<JwtTokenDTO> register(@RequestBody AccountDetailsDTO details) {
        try {
            AccountEntity account = accountService.save(details);
            String token = JwtUtil.generateToken(account);
            return ResponseEntity.ok(new JwtTokenDTO(token));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
