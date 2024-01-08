package com.openclassrooms.api.controller;

import com.openclassrooms.api.model.Message;
import com.openclassrooms.api.model.PostMessage;
import com.openclassrooms.api.service.MessageService;
import com.openclassrooms.api.service.RentalsService;
import com.openclassrooms.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller class for handling messages.
 */
@RestController
public class MessageController {
    @Autowired
    private UserService userService;

    @Autowired
    private RentalsService rentalsService;

    @Autowired
    private MessageService messageService;

    /**
     * Endpoint for posting a message.
     *
     * @param postMessage The request containing message details.
     * @return The posted message.
     */
    @PostMapping(value = "/api/messages")
    public Message postMessage(@RequestBody PostMessage postMessage) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        userService.findByMail(authentication.getName());
        userService.getUserById(postMessage.getUser_id());
        rentalsService.getRentalsDtoById(postMessage.getRental_id());


        return messageService.postMessage(postMessage);
    }
}
