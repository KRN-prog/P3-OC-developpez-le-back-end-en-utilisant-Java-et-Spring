package com.openclassrooms.api.repository;

import com.openclassrooms.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByMail(String mail);

    Optional<User> findByMailAndPassword(String mail, String password);

    Optional<User> findById(int userId);
}
