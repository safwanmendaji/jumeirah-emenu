package com.jumeirah.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "roles")
@Data
public class Role {

    @Id
    private UUID id = UUID.randomUUID();

    private String roleName;
}
