package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }
    
    @PostMapping("register")
    public ResponseEntity<Account> registerUser(@RequestBody Account account){
        Account newAccount = accountService.addAccount(account);
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
        Account loggedinAccount = accountService.getAccount(account);
        if (loggedinAccount == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(loggedinAccount);
        }
    }

    @PostMapping("messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        Message newMessage = messageService.addMessage(message);
        if (newMessage == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(newMessage);
        }
    }

    @GetMapping("messages")
    public ResponseEntity<List<Message>> retrieveMessages(){
        List<Message> allMessages = messageService.getAllMssages();
        return ResponseEntity.status(HttpStatus.OK).body(allMessages);
    } 

    @GetMapping("messages/{messageId}")
    public ResponseEntity<Message> retrieveMessageById(@PathVariable int messageId){
        Message message = messageService.getMessageById(messageId);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping("messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable int messageId){
        int deletedRows = messageService.deleteMessageById(messageId);
        if (deletedRows == 0){
            return ResponseEntity.status(HttpStatus.OK).build();
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(1);
        }
    }

}
