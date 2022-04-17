package com.william.yataco.testservice.application.mapper;

import com.william.yataco.testservice.application.data.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class DemoMapperTest {

    @Autowired
    private DemoMapper demoMapper;


    @Test
    void mapperRequest_whenGetParameters_thenOK() {

        String userName="demo";
        String password="demo";

        UserRequest userRequest = demoMapper.mapper(userName,password);

        assertEquals(userName, userRequest.getUsername());
        assertEquals(password, userRequest.getPassword());

    }
}
