package com.william.yataco.testservice.infraestructure.provider.restclient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.william.yataco.testservice.application.data.UserRequest;
import com.william.yataco.testservice.domain.model.Movement;
import com.william.yataco.testservice.domain.model.User;
import com.william.yataco.testservice.domain.model.UserToken;
import com.william.yataco.testservice.infraestructure.configuration.ConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class RestClientProviderImpl implements RestClientProvider {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ConfigProperties configProperties;

    public RestClientProviderImpl(RestTemplate restTemplate, ObjectMapper objectMapper, ConfigProperties configProperties) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.configProperties = configProperties;
    }

    @Override
    public UserToken loginToUser(UserRequest userRequest) {
        UserToken userToken = restTemplate.postForObject(configProperties.getUrlLogin(),userRequest,UserToken.class);
        return userToken;
    }

    @Override
    public User getInfoUser(String authorization) {
        HttpHeaders headers = getHeaders(authorization);
        HttpEntity<User> jwtEntity = new HttpEntity<User>(headers);
        ResponseEntity<User> userResponseEntity = restTemplate.exchange(configProperties.getUrlMe(), HttpMethod.GET, jwtEntity,
                User.class);
        return userResponseEntity.getBody();
    }

    @Override
    public List<Movement> getMovements(String authorization, String identifierUser, int offSet) {
        HttpHeaders headers = getHeaders(authorization);
        HttpEntity<Movement> movementHttpEntity = new HttpEntity<Movement>(headers);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", identifierUser);
        parameters.put("offset", String.valueOf(offSet));
        parameters.put("max", configProperties.getListMax());
        ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(configProperties.getUrlMovement(),HttpMethod.GET,movementHttpEntity,JsonNode.class,parameters);
        JsonNode jsonNodeResponse = responseEntity.getBody();
        List<Movement> movementList = objectMapper.convertValue(jsonNodeResponse.get("data"), new TypeReference<List<Movement>>(){});
        return movementList;
    }

    private HttpHeaders getHeaders(String authorization) {
        HttpHeaders headers = new HttpHeaders();
        String token = "Bearer " + authorization;
        headers.set("Authorization", token);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
