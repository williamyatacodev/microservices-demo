package com.william.yataco.testservice.infraestructure.provider.restclient.handler;

import com.william.yataco.testservice.domain.handler.DemoTemplateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseActions;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@ExtendWith(SpringExtension.class)
@RestClientTest
@ActiveProfiles("test")
class DemoErrorHandlerTest {

    @Autowired
    private MockRestServiceServer mockRestServiceServer;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    private ResponseActions responseActions;
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        restTemplate = this.restTemplateBuilder
                .errorHandler(new DemoErrorHandler())
                .build();
        responseActions = MockRestServiceServer.createServer(restTemplate)
                .expect(requestTo("/demo"))
                .andExpect(method(HttpMethod.GET));
    }

    @Test
    void  givenRemoteApiCall_when404Error_thenThrowUnauthorized() {

        responseActions.andRespond(withUnauthorizedRequest());

        assertThrows(DemoTemplateException.class, () -> {
            String response = restTemplate.getForObject("/demo", String.class);
        });
    }

    @Test
    void  givenRemoteApiCall_when500Error_thenThrowServerError() {

        responseActions.andRespond(withServerError());

        assertThrows(DemoTemplateException.class, () -> {
            String response = restTemplate.getForObject("/demo", String.class);
        });
    }

    @Test
    void  givenRemoteApiCall_when204_thenThrowNotContent() {

        responseActions.andRespond(withNoContent());

        String response = restTemplate.getForObject("/demo", String.class);

        assertNull(response);
    }

    @Test
    void  givenRemoteApiCall_when302_thenThrowFound() {

        responseActions.andRespond(withStatus(HttpStatus.FOUND));

        String response = restTemplate.getForObject("/demo", String.class);

        assertNull(response);
    }
}
