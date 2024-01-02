package com.openclassrooms.api.repository;

import com.openclassrooms.api.model.Rentals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rentals, Integer> {
    Optional<Rentals> findById(int rentalId);
    List<Rentals> findAll();
}
