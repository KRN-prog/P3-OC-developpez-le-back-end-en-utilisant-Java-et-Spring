package com.openclassrooms.api.mapper;

import com.openclassrooms.api.dto.UserDto;
import com.openclassrooms.api.model.User;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-08T08:39:59+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_392 (Amazon.com Inc.)"
)
public class AutoUserMapperImpl implements AutoUserMapper {

    @Override
    public UserDto mapToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        return userDto;
    }
}
