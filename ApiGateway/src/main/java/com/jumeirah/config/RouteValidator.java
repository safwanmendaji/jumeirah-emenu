package com.jumeirah.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    // ✅ Only these endpoints are accessible without a token
    public static final List<String> openApiEndpoints = List.of(
            "/api/auth/login",
            "/api/user/register",
            "/api/role/create",
            "/eureka",
            "/qr/**",
            "/api/restaurant/getInfo/**",
            "/api/menu/byrestaurantid/**",
            "/api/order/place"

    );

    private static final AntPathMatcher pathMatcher = new AntPathMatcher();


    // ✅ All other requests require token
    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(pattern -> pathMatcher.match(pattern, request.getURI().getPath()));
    // Optional: Check if request is from localhost
    public boolean isRequestFromLocalhost(ServerHttpRequest request) {
        String origin = request.getHeaders().getOrigin();
        return origin != null && origin.equals("http://localhost:3000");
    }
}
