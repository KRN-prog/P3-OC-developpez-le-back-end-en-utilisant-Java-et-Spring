package com.openclassrooms.api.configuration;


import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

/**
 * This configuration class is for Spring Security settings, JWT decoding and encoding, and password encoding
 * @author Kyrian ANIECOLE
 */
@Configuration
public class SpringSecurityConfig {

    private String jwtKey = "bEXocErmKBCQVKanPjIJ6Ojpoiuytrezamlkjhgfdsq";


    /**
     * This class configures the security filter chain for HTTP requests
     *
     * @param http the HttpSecurity object to configure the security settings
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs during configuration
     * @author Kyrian ANIECOLE
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests()
                    .antMatchers("/api/user/{userId}", "/api/rentals", "/api/rentals/{rentalId}", "/api/auth/register", "/api/auth/login").permitAll()
                    .anyRequest().authenticated()
                .and()
                .httpBasic(Customizer.withDefaults())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }


    /**
     * This class configures the JWT decoder bean for decoding JWT tokens
     *
     * @return the configured JwtDecoder bean
     * @author Kyrian ANIECOLE
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKeySpec secretKey = new SecretKeySpec(this.jwtKey.getBytes(), 0, this.jwtKey.getBytes().length,"RSA");
        return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
    }


    /**
     * This class configures the JWT encoder bean for encoding JWT tokens
     *
     * @return the configured JwtEncoder bean
     * @author Kyrian ANIECOLE
     */
    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(this.jwtKey.getBytes()));
    }


    /**
     * This class configures the BCryptPasswordEncoder bean for encoding passwords
     *
     * @return the configured BCryptPasswordEncoder bean
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }
}
