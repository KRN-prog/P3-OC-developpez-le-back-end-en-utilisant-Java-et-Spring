package com.openclassrooms.api.service;

import com.openclassrooms.api.model.LoginUser;
import com.openclassrooms.api.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * This service class allow the handling of JWT operations.
 */
@Service
public class JWTService {
    private JwtEncoder jwtEncoder;

    /**
     * The constructor for JWTService
     *
     * @param jwtEncoder The JwtEncoder used for encoding JWTs
     * @author Kyrian ANIECOLE
     */
    public JWTService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }


    /**
     * This generates a JWT token with the user information or login request
     *
     * @param userDto          The UserDto containing user information
     * @param loginRequest     The LoginUser containing login request information
     * @param authentication   The Authentication object
     * @return A JWT token string
     * @author Kyrian ANIECOLE
     */
    public String genrerateToken(UserDto userDto, LoginUser loginRequest, Authentication authentication) {
        String emailRequest;
        if (userDto != null) {
            emailRequest = userDto.getMail();
        }else{
            emailRequest = loginRequest.getEmail();
        }
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(emailRequest)
                .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }
}
