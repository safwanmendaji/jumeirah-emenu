package com.jumeirah.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class UserInfo {

    @Id
    private UUID id = UUID.randomUUID();

    private String name;
    @Indexed(unique = true)
    private String email;
    private String password;

    private String mobileNo;
    private String address;

    private boolean isActive = true;
    private boolean isDeleted = false;

    private Date createdAt = new Date();
    private Date updatedAt = new Date();

    @DBRef
    private Role role;

    private UUID restaurantId;

}
