package com.openclassrooms.api.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rentals")
public class Rentals {
    // Déclaration des getters/setters de ce que conntient l'interface Rentals
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surface")
    private int surface;

    @Column(name = "price")
    private int price;

    @Column(name = "picture")
    private String picture;

    @Column(name = "description")
    private String description;

    @Column(name = "owner_id")
    private int ownerId;

    @Column(name = "created_at", columnDefinition = "DATE")
    private String createdAt;

    @Column(name = "updated_at", columnDefinition = "DATE")
    private String updatedAt;
}
