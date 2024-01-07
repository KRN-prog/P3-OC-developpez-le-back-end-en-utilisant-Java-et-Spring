package com.openclassrooms.api.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutRentals {
    private String name;
    private int surface;
    private int price;
    private String description;
}
