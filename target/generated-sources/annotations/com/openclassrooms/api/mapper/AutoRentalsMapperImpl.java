package com.openclassrooms.api.mapper;

import com.openclassrooms.api.dto.RentalsDto;
import com.openclassrooms.api.model.Rentals;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-08T08:39:59+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_392 (Amazon.com Inc.)"
)
public class AutoRentalsMapperImpl implements AutoRentalsMapper {

    @Override
    public RentalsDto mapToRentalsDto(Rentals rentals) {
        if ( rentals == null ) {
            return null;
        }

        RentalsDto rentalsDto = new RentalsDto();

        return rentalsDto;
    }
}
