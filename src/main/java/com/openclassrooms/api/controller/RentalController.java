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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
public class RentalController {

    @Autowired
    private UserService userService;

    @Autowired
    private RentalsService rentalsService;

    @GetMapping(value = "/api/rentals")
    public ResponseEntity<Map<String, List<RentalsDto>>> getAllRentals() {

        Map<String, List<RentalsDto>> rentals = new HashMap<>();
        rentals.put("rentals", rentalsService.getAllRentals());
        return ResponseEntity.ok(rentals);
    }

    @GetMapping(value = "/api/rentals/{rentalId}")
    public RentalsDto getRentalsById(@PathVariable("rentalId") Integer rentalId) {
        return rentalsService.getRentalsDtoById(rentalId);
    }

    @PutMapping(value = "/api/rentals/{rentalId}")
    public ResponseEntity<String> updateRental(@PathVariable Integer rentalId, @RequestParam Map<String, String> bodyForm){
        rentalsService.rentalUpdate(rentalId, bodyForm);
        return ResponseEntity.ok("{ \"message\": \"Rental updated !\" }");
    }

    @PostMapping(value = "/api/rentals")
    public ResponseEntity<String> createRentals(@ModelAttribute CreateRentalRequest createRentalRequest, @RequestParam("picture") MultipartFile file, Model model) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDto userDto = userService.findByMail(authentication.getName());
        rentalsService.createRentals(createRentalRequest, file, userDto.getId());
        return ResponseEntity.ok("{ \"message\": \"Rental created !\" }");
    }
}
