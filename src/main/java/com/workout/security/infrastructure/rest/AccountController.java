package com.workout.security.infrastructure.rest;

import com.workout.security.domain.dto.AccountDetailsDTO;
import com.workout.security.domain.model.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
