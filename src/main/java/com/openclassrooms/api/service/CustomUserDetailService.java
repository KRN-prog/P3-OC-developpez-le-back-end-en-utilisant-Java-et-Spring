package com.openclassrooms.api.service;


import com.openclassrooms.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.openclassrooms.api.model.User myUser = userRepository.findByMail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email=" + email));
        return User
                .withUsername(myUser.getMail())
                .password(new BCryptPasswordEncoder().encode(myUser.getPassword()))
                .authorities("USER")
                .build();
    }
}
