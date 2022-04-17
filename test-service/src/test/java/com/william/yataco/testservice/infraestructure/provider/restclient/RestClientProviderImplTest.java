package com.william.yataco.testservice.infraestructure.provider.restclient;

import com.william.yataco.testservice.application.data.UserRequest;
import com.william.yataco.testservice.domain.common.Util;
import com.william.yataco.testservice.domain.model.Movement;
import com.william.yataco.testservice.domain.model.Movements;
import com.william.yataco.testservice.domain.model.User;
import com.william.yataco.testservice.domain.model.UserToken;
import com.william.yataco.testservice.infraestructure.configuration.ConfigProperties;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Log4j2
@RestClientTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@EnableConfigurationProperties(value = ConfigProperties.class)
@ActiveProfiles("test")
class RestClientProviderImplTest {
    @MockBean
    private RestTemplate restTemplate;
    @Autowired
    private ConfigProperties configProperties;
    @InjectMocks
    private RestClientProviderImpl restClientProvider;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @BeforeEach
    void beforeInit() {
        restClientProvider = new RestClientProviderImpl(restTemplate,configProperties);
    }

    @Test
    void loginToUser_whenLoginWithCredentialsUser_thenOk() {
       UserToken userTokenResponse = new UserToken();
       userTokenResponse.setAccessToken("token");
       UserRequest userRequest = new UserRequest();

       when(restTemplate.postForEntity(anyString(), any(UserRequest.class), any(Class.class)))
          .thenReturn(new ResponseEntity(userTokenResponse,HttpStatus.OK));

       UserToken userToken = restClientProvider.loginToUser(userRequest);
       assertEquals(userTokenResponse, userToken);

       verify(restTemplate).postForEntity(anyString(), any(UserRequest.class), any(Class.class));
    }

    @Test
    void getInfoUser_whenGetInformationUserWithToken_thenOk() {
        User userResponse = new User();
        userResponse.setId(UUID.randomUUID().toString());
        String token = "token";
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
                .thenReturn(ResponseEntity.ok(userResponse));

        User user = restClientProvider.getInfoUser(token);
        assertEquals(userResponse, user);

        verify(restTemplate).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class));
    }

    @Test
    void getMovements_whenGetMovementWithToken_thenOk() {
        String token = "token";
        String identifier = UUID.randomUUID().toString();
        Movements movementsResponse = new Movements();
        List<Movement> movementList = Util.buildListMovement();
        movementsResponse.setData(movementList);

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class),any(Map.class)))
                .thenReturn(ResponseEntity.ok(movementsResponse));

        Movements movements = restClientProvider.getMovements(token,identifier,0);

        assertEquals(movementsResponse, movements);

        verify(restTemplate).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class),any(Map.class));
    }
}
