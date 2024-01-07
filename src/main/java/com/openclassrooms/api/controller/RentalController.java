package com.openclassrooms.api.controller;

import com.openclassrooms.api.request.CreateRentalRequest;
import com.openclassrooms.api.service.RentalsService;
import com.openclassrooms.api.service.UserService;
import com.openclassrooms.api.dto.RentalsDto;
import com.openclassrooms.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


/**
 * This RestController class allows you to create, modificate, update, ... a rental
 * @author Kyrian ANIECOLE
 */
@RestController
public class RentalController {

    @Autowired
    private UserService userService;

    @Autowired
    private RentalsService rentalsService;

    /**
     * This endpoint allows you to get all rentals in the database
     *
     * @return A ResponseEntity containing a map of rental data
     * @author Kyrian ANIECOLE
     */
    @GetMapping(value = "/api/rentals")
    public ResponseEntity<Map<String, List<RentalsDto>>> getAllRentals() {

        Map<String, List<RentalsDto>> rentals = new HashMap<>();
        rentals.put("rentals", rentalsService.getAllRentals());
        return ResponseEntity.ok(rentals);
    }


    /**
     * This endpoint allows you to retrieve a rental by its ID
     *
     * @param rentalId The ID of the rental to retrieve
     * @return The RentalsDto representing the rental
     * @author Kyrian ANIECOLE
     */
    @GetMapping(value = "/api/rentals/{rentalId}")
    public RentalsDto getRentalsById(@PathVariable("rentalId") Integer rentalId) {
        return rentalsService.getRentalsDtoById(rentalId);
    }


    /**
     * This endpoint allows you to update a rental by his id
     *
     * @param rentalId   The ID of the rental to update
     * @param bodyForm   The updated rental information
     * @return A ResponseEntity indicating that the rental has been updated
     * @author Kyrian ANIECOLE
     */
    @PutMapping(value = "/api/rentals/{rentalId}")
    public ResponseEntity<String> updateRental(@PathVariable Integer rentalId, @RequestParam Map<String, String> bodyForm){
        rentalsService.rentalUpdate(rentalId, bodyForm);
        return ResponseEntity.ok("{ \"message\": \"Rental updated !\" }");
    }


    /**
     * This endpoint allows you to create a new rental
     *
     * @param createRentalRequest The request containing rental details
     * @param file                The rental picture file
     * @return A ResponseEntity indicating that a new rental has been created
     * @throws IOException If there's an issue with IO operations
     * @author Kyrian ANIECOLE
     */
    @PostMapping(value = "/api/rentals")
    public ResponseEntity<String> createRentals(@ModelAttribute CreateRentalRequest createRentalRequest, @RequestParam("picture") MultipartFile file) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDto userDto = userService.findByMail(authentication.getName());
        rentalsService.createRentals(createRentalRequest, file, userDto.getId());
        return ResponseEntity.ok("{ \"message\": \"Rental created !\" }");
    }
}
