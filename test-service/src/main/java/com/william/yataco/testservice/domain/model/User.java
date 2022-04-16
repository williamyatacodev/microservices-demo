package com.william.yataco.testservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;
    private boolean accountExpired;
    private boolean accountLocked;
    private long customerId;
    private String dateCreated;
    private String email;
    private boolean enabled;
    private String lastUpdated;
    private String name;
}
