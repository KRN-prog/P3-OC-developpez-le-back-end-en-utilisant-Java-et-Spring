package com.openclassrooms.api.service;

import com.openclassrooms.api.model.Message;
import com.openclassrooms.api.model.PostMessage;
import com.openclassrooms.api.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This service class allow to post a message on rentals
 * @author Kyrian ANIECOLE
 */
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;


    /**
     * This posts a message to the database (with the repository)
     *
     * @param postMessage The PostMessage containing message details
     * @return The posted Message
     * @author Kyrian ANIECOLE
     */
    public Message postMessage(PostMessage postMessage) {
        Message message = new Message();
        message.setRentalId(postMessage.getRental_id());
        message.setUserId(postMessage.getUser_id());
        message.setMessage(postMessage.getMessage());
        return messageRepository.save(message);
    }
}
