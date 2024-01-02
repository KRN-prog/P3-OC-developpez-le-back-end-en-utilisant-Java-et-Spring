package com.openclassrooms.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRentalRequest {
    private String name;
    private int  surface;
    private int  price;
    private String description;
    private int ownerId;
}
