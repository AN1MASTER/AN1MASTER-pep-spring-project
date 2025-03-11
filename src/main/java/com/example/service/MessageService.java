package com.example.service;

import java.util.ArrayList;
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
    public MessageService (MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
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
        Optional<Message> message = this.messageRepository.findById(message_id);
        if (message.isEmpty()) {
            return null;
        }
        return message;
    } 

    

    public Integer deleteMessageById(int message_id) {
        this.messageRepository.deleteById(message_id);
        return 1;  
    }

    
    public Integer updateMessageById(int message_id, String messageText) {
        Optional<Message> message = this.messageRepository.findById(message_id);
        Message updatedMessage = message.get();
        if (messageText.isBlank() || messageText.length() > 255) {
            throw new InvalidMessageException();
            
        } else {
            updatedMessage.setMessageText(messageText);
            this.messageRepository.save(updatedMessage);
            return 1;
        }
    }  

    public List<Message> getAccountMessages(int account_id){
        List<Message> messages = this.messageRepository.findAll();
        List<Message> accountMessages = new ArrayList<Message>();

        for(Message message: messages) {
            if (message.getPostedBy() == account_id) {
                System.out.println(message.getMessageText());
                accountMessages.add(message);
            }
        }
        return accountMessages;
    } 

    
}