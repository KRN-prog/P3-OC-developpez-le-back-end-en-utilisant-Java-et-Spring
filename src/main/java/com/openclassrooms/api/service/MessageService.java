package com.openclassrooms.api.service;

import com.openclassrooms.api.model.Message;
import com.openclassrooms.api.model.PostMessage;
import com.openclassrooms.api.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message postMessage(PostMessage postMessage) {
        Message message = new Message();
        message.setRentalId(postMessage.getRental_id());
        message.setUserId(postMessage.getUser_id());
        message.setMessage(postMessage.getMessage());
        return messageRepository.save(message);
    }
}
