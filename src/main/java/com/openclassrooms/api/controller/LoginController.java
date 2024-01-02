package com.openclassrooms.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.api.model.LoginUser;
import com.openclassrooms.api.model.TokenRespons;
import com.openclassrooms.api.service.JWTService;
import com.openclassrooms.api.service.UserService;
import com.openclassrooms.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    public JWTService jwtService;

    public LoginController (JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping(value = "/api/auth/login")
    public ResponseEntity<String> getToken(@RequestBody LoginUser loginRequest, Authentication authentication) throws JsonProcessingException {
        userService.findByMailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

        String token = jwtService.genrerateToken(null, loginRequest, authentication);

        TokenRespons tokenRespons = new TokenRespons();
        tokenRespons.setToken(token);

        // Convertir l'objet en JSON
        ObjectMapper objectMapper = new ObjectMapper();

        return ResponseEntity.ok(objectMapper.writeValueAsString(tokenRespons));
    }

    @GetMapping(value = "api/auth/me")
    public UserDto createUserMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByMail(authentication.getName());
    }

    @PostMapping(value = "api/auth/register")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto, Authentication authentication) throws JsonProcessingException {
        userService.createUsers(userDto);

        String token = jwtService.genrerateToken(userDto, null, authentication);

        TokenRespons tokenRespons = new TokenRespons();
        tokenRespons.setToken(token);

        // Convertir l'objet en JSON
        ObjectMapper objectMapper = new ObjectMapper();

        return ResponseEntity.ok(objectMapper.writeValueAsString(tokenRespons));
    }
}
