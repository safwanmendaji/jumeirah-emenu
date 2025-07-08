package com.jumeirah.filter;

import com.jumeirah.config.RouteValidator;

import com.jumeirah.util.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.file.AccessDeniedException;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtService jwtService;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {

            System.out.println("hello");
            if (validator.isSecured.test(exchange.getRequest())) {

                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                }



                String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    throw new RuntimeException("Invalid authorization header");
                }

                // Remove "Bearer " prefix
                String token = authHeader.substring(7);

                try {
//                    // URL of your UserService validate endpoint
//                    String url = "http://localhost:9096/api/auth/validate?token=" + token;
//
//                    // Make the GET request to UserService
//                    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);



                    if ( jwtService.isTokenValid(token)) {
                        return chain.filter(exchange); // Proceed if token is valid
                    } else {
                        throw new RuntimeException("Unauthorized access - token invalid");
                    }

                } catch (Exception e) {
                    System.out.println("Token validation failed: " + e.getMessage());
                    throw new RuntimeException("Token validation failed");
                }
            }

            return chain.filter(exchange); // No validation needed for public routes
        });
    }

    public static class Config {
        // Empty config class required by Spring Cloud Gateway
    }
}
