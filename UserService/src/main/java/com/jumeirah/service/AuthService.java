package com.jumeirah.service;

import com.jumeirah.dto.LoginRequestDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> login(LoginRequestDto request);
}
