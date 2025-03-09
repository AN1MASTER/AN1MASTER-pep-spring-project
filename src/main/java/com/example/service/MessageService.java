package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    
    private MessageRepository messageRepository;
    
    @Autowired
    public MessageService (MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages() {
        return this.messageRepository.findAll();
    }

    /* public Message getMessageById(int message_id) throws ResourceNotFoundException{
        return this.messageRepository.findById(message_id).orElseThrow(() -> new ResourceNotFoundException());
    } */

    /* public int deleteMessageById(int message_id) {
        this.messageRepository.deleteById(message_id);
    }

    @Bean
    public Message updateMessageById(int message_id) {
        return this.messageRepository.
    } */

    /* public Message getMessageById(int message_id) throws ResourceNotFoundException{
        
        return this.messageRepository.findById(message_id).orElse(() -> throw);
    } */

    
}