package com.william.yataco.testservice.application.mapper;

import com.william.yataco.testservice.application.data.UserRequest;
import org.springframework.stereotype.Component;

@Component
public class DemoMapper {

    public UserRequest mapper(String userName, String password) {
        return UserRequest.builder().username(userName).password(password).build();
    }
}
