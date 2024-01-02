package com.openclassrooms.api.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "rental_id")
    private int rentalId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "message")
    private String message;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;
}
