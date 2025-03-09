package com.example.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.UsernameExistsException;
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

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }
    
    @PostMapping("/register")
    public @ResponseBody ResponseEntity<Account> register(@RequestBody Account account) throws UsernameExistsException{
        Account regAccount = this.accountService.addAccount(account);
        if (regAccount == null) {
            return ResponseEntity.status(409).body(regAccount);
        }

        return ResponseEntity.status(200).body(regAccount);
    } 
    @ExceptionHandler (value = UsernameExistsException.class)
    public @ResponseBody ResponseEntity userNameExitsExceptionHandler(UsernameExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    } 

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account){
        Account loginAccount = this.accountService.login(account.getUsername(), account.getPassword());
        if (loginAccount == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginAccount);
        }
        return ResponseEntity.status(HttpStatus.OK).body(loginAccount);
    }
    

    

    @PostMapping("/messages")
    public ResponseEntity createMessage(@RequestBody Message message) {
        return null;
    }

    @GetMapping("/messages")
    public ResponseEntity getAllMessages(@RequestBody Message message) {
        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }

    @GetMapping("/messages/{message_id}")
    public ResponseEntity getMessageById(@PathVariable int message_id) {
        return null;
    }

    /* @DeleteMapping("/messages/{message_id}")
    public ResponseEntity deleteMessageById(@PathVariable int message_id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.messageService.deleteMessageById(message_id));
    } */ 

    /* @PatchMapping("/messages/{message_id}")
    public ResponseEntity updateMessageById(@PathVariable int message_id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.messageService.updateMessageById(message_id));
    } */

    @GetMapping("/account/{account_id}/messages")
    public ResponseEntity getMessagesByAccountId(@PathVariable int account_id) {
        return null;
    }
}
