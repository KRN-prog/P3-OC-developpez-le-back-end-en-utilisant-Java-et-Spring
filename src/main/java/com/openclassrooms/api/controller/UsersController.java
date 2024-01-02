package com.openclassrooms.api.controller;

import com.openclassrooms.api.model.*;
import com.openclassrooms.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/api/user/{userId}")
    public Optional<User> getUserById(@PathVariable("userId") Integer userId) {
        return userService.getUserById(userId);
    }
}
