package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message addMessage(Message message){
        if (message.getMessageText().isBlank()){
            return null;
        }
        if (message.getMessageText().length() > 255){
            return null;
        }
        if (accountRepository.findById(message.getPostedBy()).isEmpty()){
            return null;
        }
        return messageRepository.save(message);
    }

    public List<Message> getAllMssages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(int messageId){
        Optional<Message> message = messageRepository.findById(messageId);
        if (message.isEmpty()){
            return null;
        }
        return message.get();
    }

}
