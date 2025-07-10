package com.jumeirah.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class RoleDto {

    private UUID  id;
    private String roleName;
}
