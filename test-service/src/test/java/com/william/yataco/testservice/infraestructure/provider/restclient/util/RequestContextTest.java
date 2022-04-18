package com.william.yataco.testservice.infraestructure.provider.restclient.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
class RequestContextTest {

    @Test
    void threadLocal_whenRequestContext_thenOk() {
        String value = UUID.randomUUID().toString();
        RequestContext.getContext().setToken(value);
        String token = RequestContext.getContext().getToken();
        assertEquals(value,token);
        RequestContext.getContext().clear();
    }
}
