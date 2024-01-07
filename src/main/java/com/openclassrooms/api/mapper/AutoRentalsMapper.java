package com.openclassrooms.api.mapper;

import com.openclassrooms.api.model.Rentals;
import com.openclassrooms.api.dto.RentalsDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * This Mapper interface allows you the convertion between {@link Rentals} and {@link RentalsDto}
 * @author Kyrian ANIECOLE
 */
@Mapper
public interface AutoRentalsMapper {

    /**
     * This is an instance of AutoRentalsMapper (Use this instance if you want to mapp operations)
     * @author Kyrian ANIECOLE
     */
    AutoRentalsMapper MAPPER = Mappers.getMapper(AutoRentalsMapper.class);

    /**
     * This converts a {@link Rentals} object to a {@link RentalsDto} object.
     *
     * @param rentals The source Rentals object
     * @return A mapped RentalsDto object
     * @author Kyrian ANIECOLE
     */
    RentalsDto mapToRentalsDto(Rentals rentals);
}
