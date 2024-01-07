package com.openclassrooms.api.model;


import lombok.Getter;

@Getter
public class PostMessage {
    private int user_id;
    private String message;
    private int rental_id;
}
