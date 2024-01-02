package com.openclassrooms.api.mapper;

import com.openclassrooms.api.model.Rentals;
import com.openclassrooms.api.dto.RentalsDto;

public class RentalMapper {
    public static RentalsDto mapToRentalsDto(Rentals rentals) {
        return new RentalsDto(
                rentals.getId(),
                rentals.getName(),
                rentals.getSurface(),
                rentals.getPrice(),
                rentals.getPicture(),
                rentals.getDescription(),
                rentals.getOwnerId(),
                rentals.getCreatedAt(),
                rentals.getUpdatedAt()
        );
    }

    public static Rentals mapToRentals(RentalsDto rentalsDto) {
        return new Rentals(
                rentalsDto.getId(),
                rentalsDto.getName(),
                rentalsDto.getSurface(),
                rentalsDto.getPrice(),
                rentalsDto.getPicture(),
                rentalsDto.getDescription(),
                rentalsDto.getOwnerId(),
                rentalsDto.getCreatedAt(),
                rentalsDto.getUpdatedAt()
        );
    }
}
