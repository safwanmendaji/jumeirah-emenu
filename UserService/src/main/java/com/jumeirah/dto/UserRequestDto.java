package com.jumeirah.dto;


import lombok.Data;

import java.util.UUID;

@Data
public class UserRequestDto {
    private String name;
    private String email;
    private String password;
    private String mobileNo;
    private String address;
    private UUID roleId;
    private UUID restaurantId;
}
