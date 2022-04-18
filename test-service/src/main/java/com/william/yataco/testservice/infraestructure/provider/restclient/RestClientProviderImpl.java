package com.william.yataco.testservice.infraestructure.provider.restclient;

import com.william.yataco.testservice.application.data.UserRequest;
import com.william.yataco.testservice.domain.model.Movements;
import com.william.yataco.testservice.domain.model.User;
import com.william.yataco.testservice.domain.model.UserToken;
import com.william.yataco.testservice.infraestructure.configuration.ConfigProperties;
import com.william.yataco.testservice.infraestructure.provider.restclient.util.RequestContext;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Component
public class RestClientProviderImpl implements RestClientProvider {

    private final RestTemplate restTemplate;
    private final ConfigProperties configProperties;

    public RestClientProviderImpl(RestTemplate restTemplate, ConfigProperties configProperties) {
        this.restTemplate = restTemplate;
        this.configProperties = configProperties;
    }
    @Override
    public void loginToUser(UserRequest userRequest) {
        log.info("loginToUser()");
        ResponseEntity<UserToken> responseEntity = restTemplate.postForEntity(configProperties.getUrlLogin(),userRequest,UserToken.class);
        UserToken userToken = responseEntity.getBody();
        RequestContext.getContext().setToken(userToken.getAccessToken());
    }
    @Override
    public User getInfoUser() {
        log.info("getInfoUser()");
        HttpHeaders headers = getHeaders();
        HttpEntity<?> jwtEntity = new HttpEntity<>(headers);
        ResponseEntity<User> userResponseEntity = restTemplate.exchange(configProperties.getUrlMe(), HttpMethod.GET, jwtEntity,
                User.class);
        return userResponseEntity.getBody();
    }
    @Override
    public Movements getMovements(String identifierUser, int offSet) {
        log.info("getMovements()");
        HttpHeaders headers = getHeaders();
        HttpEntity<?> movementHttpEntity = new HttpEntity<>(headers);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", identifierUser);
        parameters.put("offset", String.valueOf(offSet));
        parameters.put("max", configProperties.getListMax());
        ResponseEntity<Movements> responseEntity = restTemplate.exchange(configProperties.getUrlMovement(),HttpMethod.GET,movementHttpEntity,
                Movements.class,parameters);
        return responseEntity.getBody();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
