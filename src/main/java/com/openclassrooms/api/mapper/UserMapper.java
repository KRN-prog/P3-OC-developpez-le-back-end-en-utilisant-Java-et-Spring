package com.openclassrooms.api.mapper;

import com.openclassrooms.api.model.User;
import com.openclassrooms.api.dto.UserDto;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getMail(),
                user.getName(),
                user.getPassword(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public static User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getMail(),
                userDto.getName(),
                userDto.getPassword(),
                userDto.getCreatedAt(),
                userDto.getUpdatedAt()
        );
    }
}
