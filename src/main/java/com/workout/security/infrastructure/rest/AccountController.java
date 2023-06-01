package com.workout.security.infrastructure.rest;

import com.workout.security.application.config.JwtUtil;
import com.workout.security.domain.dto.AccountDetailsDTO;
import com.workout.security.domain.model.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @PostMapping("register")
    public void register(@RequestBody AccountDetailsDTO details) {
        accountService.save(details);
    }

    @GetMapping("auth")
    public String auth() {
        return JwtUtil.generateToken();
    }
}
