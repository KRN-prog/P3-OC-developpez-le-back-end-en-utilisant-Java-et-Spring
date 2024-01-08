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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

/**
 * This service class allows you to handle rental operations (get all, update, create)
 * @author Kyrian ANIECOLE
 */
@Service
public class RentalsService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private S3Service s3Service;


    /**
     * This class retrieve list of all rentals
     *
     * @return A list of RentalsDto representing all rentals
     * @author Kyrian ANIECOLE
     */
    public List<RentalsDto> getAllRentals() {
        List<Rentals> rentals = rentalRepository.findAll();

        return rentals.stream()
                .map(RentalMapper::mapToRentalsDto)
                .collect(Collectors.toList());
    }


    /**
     * This class retrieve a rental by its id
     *
     * @param rentalId The ID of the rental to retrieve
     * @return A RentalsDto representing the requested rental
     * @author Kyrian ANIECOLE
     */
    public RentalsDto getRentalsDtoById(int rentalId) {
        Rentals rentals = rentalRepository.findById(rentalId).get();

        return RentalMapper.mapToRentalsDto(rentals);
    }


    /**
     * This class update a rental by its id
     *
     * @param rentalId  The ID of the rental to update
     * @param bodyForm  The updated rental information
     * @return The updated Rentals object
     * @author Kyrian ANIECOLE
     */
    public Rentals rentalUpdate(int rentalId, Map<String, String> bodyForm) {
        Rentals rental = rentalRepository.findById(rentalId).get();

        rental.setName(bodyForm.get("name"));
        rental.setSurface(parseInt(bodyForm.get("surface")));
        rental.setPrice(parseInt(bodyForm.get("price")));
        rental.setDescription(bodyForm.get("description"));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        rental.setUpdatedAt(dtf.format(now));

        return rentalRepository.save(rental);
    }


    /**
     * This class allows you to create a new rental
     *
     * @param createRentalRequest The request containing rental details
     * @param file                The rental picture file
     * @param ownerId             The ID of the owner of the rental
     * @return A RentalsDto representing the created rental
     * @author Kyrian ANIECOLE
     */
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
