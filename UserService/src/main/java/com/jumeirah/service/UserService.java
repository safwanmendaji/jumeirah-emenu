package com.jumeirah.service;

import com.jumeirah.dto.LoginRequestDto;
import com.jumeirah.dto.UserDto;
import com.jumeirah.dto.UserRequestDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> createUser(UserRequestDto userRequestDto);

}
