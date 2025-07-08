package com.jumeirah.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

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
            "/qr/test",
            "/api/order/place"

    );

    // ✅ All other requests require token
    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().startsWith(uri));

    // Optional: Check if request is from localhost
    public boolean isRequestFromLocalhost(ServerHttpRequest request) {
        String origin = request.getHeaders().getOrigin();
        return origin != null && origin.equals("http://localhost:3000");
    }
}
