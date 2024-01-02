package com.openclassrooms.api.service;

import com.openclassrooms.api.mapper.AutoRentalsMapper;
import com.openclassrooms.api.mapper.RentalMapper;
import com.openclassrooms.api.request.CreateRentalRequest;
import com.openclassrooms.api.model.Rentals;
import com.openclassrooms.api.repository.RentalRepository;
import com.openclassrooms.api.dto.RentalsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@Service
public class RentalsService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private S3Service s3Service;

    public List<RentalsDto> getAllRentals() {
        List<Rentals> rentals = rentalRepository.findAll();

        return rentals.stream()
                .map(RentalMapper::mapToRentalsDto)
                .collect(Collectors.toList());
    }


    public RentalsDto getRentalsDtoById(int rentalId) {
        Rentals rentals = rentalRepository.findById(rentalId).get();

        return RentalMapper.mapToRentalsDto(rentals);
    }

    public Rentals rentalUpdate(int rentalId, Map<String, String> bodyForm) {
        Rentals rental = rentalRepository.findById(rentalId).get();

        rental.setName(bodyForm.get("name"));
        rental.setSurface(parseInt(bodyForm.get("surface")));
        rental.setPrice(parseInt(bodyForm.get("price")));
        rental.setDescription(bodyForm.get("description"));

        return rentalRepository.save(rental);
    }

    public RentalsDto createRentals(CreateRentalRequest createRentalRequest, MultipartFile file, int ownerId) {
        String uploadeImage = s3Service.uploadFile(file);

        Rentals rentals = new Rentals();
        rentals.setName(createRentalRequest.getName());
        rentals.setSurface(createRentalRequest.getSurface());
        rentals.setPrice(createRentalRequest.getPrice());
        rentals.setDescription(createRentalRequest.getDescription());
        rentals.setPicture("https://s3imagestorage.s3.eu-west-3.amazonaws.com/"+uploadeImage);
        rentals.setOwnerId(ownerId);


        Rentals saveRentals = rentalRepository.save(rentals);

        return AutoRentalsMapper.MAPPER.mapToRentalsDto(saveRentals);
    }
}
