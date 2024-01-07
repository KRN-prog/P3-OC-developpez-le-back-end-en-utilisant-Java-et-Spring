package com.openclassrooms.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalsDto {
    private int id;
    private String name;
    private int surface;
    private int price;
    private String picture;
    private String description;
    @JsonProperty("owner_id")
    private int ownerId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;

}
