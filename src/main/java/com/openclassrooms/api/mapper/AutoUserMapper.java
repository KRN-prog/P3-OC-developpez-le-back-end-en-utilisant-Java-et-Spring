package com.openclassrooms.api.mapper;

import com.openclassrooms.api.dto.RentalsDto;
import com.openclassrooms.api.model.Rentals;
import com.openclassrooms.api.model.User;
import com.openclassrooms.api.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * Mapper interface for converting between {@link User} and {@link UserDto}.
 * @author Kyrian ANIECOLE
 */
@Mapper
public interface AutoUserMapper {

    /**
     * Instance of AutoUserMapper. Use this instance for mapping operations.
     */
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    /**
     * Converts a {@link User} object to a {@link UserDto} object.
     *
     * @param user The source Rentals object.
     * @return A mapped UserDto object.
     */
    UserDto mapToUserDto(User user);
}
