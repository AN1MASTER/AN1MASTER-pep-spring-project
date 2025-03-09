package com.example.service;

import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.entity.Account;
import com.example.exception.UsernameExistsException;
import com.example.repository.AccountRepository;


@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    private MessageService messageService;

    
    @Autowired
    public AccountService(AccountRepository accountRepository, MessageService messageService) {
        this.accountRepository = accountRepository;
        this.messageService = messageService;
    }

    
    public Account addAccount(Account account) throws UsernameExistsException{
        List<Account> accounts = this.accountRepository.findAll();
        //System.out.println("Username Exists");
        if (accounts.contains(account)) {
            System.out.println("Username Exists");
            throw new UsernameExistsException();
        } else if (account.getPassword().length() < 4 || account.getUsername().isBlank()) {
            return null;
        }
        //System.out.println("Account Registered. Username: " + account.getUsername());
        return this.accountRepository.save(account);
        
    } 

    private boolean checkIfContains(Account account) {
        List<Account> accounts = this.accountRepository.findAll();

        for (Account currAccount: accounts) {
            if (account.getUsername() == currAccount.getUsername()) {
                return true;
            }
        }
        return false;
    }

    
    public Account login(String username, String password){
        List<Account> accounts = this.accountRepository.findAll();

        for(Account account: accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                System.out.println("Username: " + account.getUsername());
                return account;
            }
        }
        return null;
    }


}

