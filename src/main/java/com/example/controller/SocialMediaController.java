package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.service.AccountService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
public class SocialMediaController {

    private AccountService accountService;

    public SocialMediaController(AccountService accountService){
        this.accountService = accountService;
    }
    
    @PostMapping("register")
    public ResponseEntity<Account> registerUser(@RequestBody Account account){
        Account newAccount = accountService.registerAccount(account);
        if (newAccount == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (newAccount.getAccountId() == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(newAccount);
        }
    }

    @PostMapping("login")
    public ResponseEntity<Account> loginUser(@RequestBody Account account){
        Account loggedinAccount = accountService.loginAccount(account);
        if (loggedinAccount == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(loggedinAccount);
        }
    }
}
