package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.InvalidAccountException;
import com.example.exception.InvalidMessageException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    private AccountRepository accountRepository;
    
    @Autowired
    public MessageService (MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages() {
        return this.messageRepository.findAll();
    }

    public Message addMessage(Message message) {
        
        Optional<Account> postedBy = accountRepository.findById(message.getPostedBy());

        if (message.getMessageText().isBlank() || message.getMessageText().length() > 255) {
            throw new InvalidMessageException();
        }
        return this.messageRepository.save(message);
        
        
    }

    public Optional<Message> getMessageById(int message_id){
        return this.messageRepository.findById(message_id);
    } 

    /* public Optional<Message> getMessageByAccountId(int account_id){
        
        return this.messageRepository.findById(message_id);
    } */

    public Integer deleteMessageById(int message_id) {
        this.messageRepository.deleteById(message_id);
        return 1;  
    }

    
    public Message updateMessageById(int message_id) {
        return this.messageRepository.
    } 

    /* public Message getMessageById(int message_id) throws ResourceNotFoundException{
        
        return this.messageRepository.findById(message_id).orElse(() -> throw);
    } */

    
}