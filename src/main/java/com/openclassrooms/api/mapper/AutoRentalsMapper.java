package com.openclassrooms.api.mapper;

import com.openclassrooms.api.model.Rentals;
import com.openclassrooms.api.dto.RentalsDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoRentalsMapper {
    AutoRentalsMapper MAPPER = Mappers.getMapper(AutoRentalsMapper.class);
    RentalsDto mapToRentalsDto(Rentals rentals);
    Rentals mapToRentals(RentalsDto rentalsDto);
}
