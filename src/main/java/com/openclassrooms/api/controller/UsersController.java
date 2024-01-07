package com.openclassrooms.api.controller;

import com.openclassrooms.api.model.*;
import com.openclassrooms.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



/**
 * This RestController class allows you to retreive a user by his id
 * @author Kyrian ANIECOLE
 */
@RestController
public class UsersController {

    @Autowired
    private UserService userService;

    /**
     * This endpoint allows you to retrieve a user by his id.
     *
     * @param userId The ID of the user that you want to retrieve
     * @return An Optional containing the User if found, otherwise an empty Optional
     * @author Kyrian ANIECOLE
     */
    @GetMapping(value = "/api/user/{userId}")
    public Optional<User> getUserById(@PathVariable("userId") Integer userId) {
        return userService.getUserById(userId);
    }
}
