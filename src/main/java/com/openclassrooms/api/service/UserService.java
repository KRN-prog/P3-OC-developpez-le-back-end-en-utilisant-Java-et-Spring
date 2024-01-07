package com.openclassrooms.api.service;

import com.openclassrooms.api.mapper.UserMapper;
import com.openclassrooms.api.model.User;
import com.openclassrooms.api.repository.UserRepository;
import com.openclassrooms.api.dto.UserDto;
import com.openclassrooms.api.mapper.AutoUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * This service class handle user operations (creation, find by mail, find by mail and password)
 * @author Kyrian ANIECOLE
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    /**
     * This class create a new user
     *
     * @param userDto The UserDto containing user details
     * @return A UserDto representing the created user
     * @throws RuntimeException If the user already exists
     * @author Kyrian ANIECOLE
     */
    public UserDto createUsers (UserDto userDto) {
        Optional<User> findUser = userRepository.findByMail(userDto.getMail());
        if (findUser.isPresent()){
            throw new RuntimeException("This user already existe.");
        }
        User user = UserMapper.mapToUser(userDto);
        User saveUser = userRepository.save(user);

        return AutoUserMapper.MAPPER.mapToUserDto(saveUser);
    }


    /**
     * This class retrieve a user by his email
     *
     * @param userEmail The email of the user to retrieve
     * @return A UserDto representing the requested user
     * @author Kyrian ANIECOLE
     */
    public UserDto findByMail(String userEmail) {
        User user = userRepository.findByMail(userEmail).get();
        return UserMapper.mapToUserDto(user);
    }


    /**
     * This class retrieve a user by his email and password
     *
     * @param userEmail    The email of the user to retrieve
     * @param userPassword The password of the user to retrieve
     * @return A UserDto representing the requested user
     * @author Kyrian ANIECOLE
     */
    public UserDto findByMailAndPassword(String userEmail, String userPassword) {
        User user = userRepository.findByMailAndPassword(userEmail, userPassword).get();
        return UserMapper.mapToUserDto(user);
    }


    /**
     * This class retrieve a user by his id
     *
     * @param userId The ID of the user to retrieve
     * @return An Optional containing the User if found, otherwise an empty Optional
     * @author Kyrian ANIECOLE
     */
    public Optional<User> getUserById(int userId) {
        return userRepository.findById(userId);
    }
}