package com.openclassrooms.api.service;

import com.openclassrooms.api.mapper.UserMapper;
import com.openclassrooms.api.model.User;
import com.openclassrooms.api.repository.UserRepository;
import com.openclassrooms.api.dto.UserDto;
import com.openclassrooms.api.mapper.AutoUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto createUsers (UserDto userDto) {
        Optional<User> findUser = userRepository.findByMail(userDto.getMail());
        if (findUser.isPresent()){
            throw new RuntimeException("This user already existe.");
        }
        User user = UserMapper.mapToUser(userDto);
        User saveUser = userRepository.save(user);

        return AutoUserMapper.MAPPER.mapToUserDto(saveUser);
    }

    public UserDto findByMail(String userEmail) {
        User user = userRepository.findByMail(userEmail).get();
        return UserMapper.mapToUserDto(user);
    }

    public UserDto findByMailAndPassword(String userEmail, String userPassword) {
        User user = userRepository.findByMailAndPassword(userEmail, userPassword).get();
        return UserMapper.mapToUserDto(user);
    }

    public Optional<User> getUserById(int userId) {
        return userRepository.findById(userId);
    }
}