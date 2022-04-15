package com.william.yataco.testservice.infraestructure.provider.restclient;

import com.william.yataco.testservice.domain.model.User;
import com.william.yataco.testservice.domain.model.UserToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class RestClientProviderImpl implements RestClientProvider {

    private final RestTemplate restTemplate;

    public RestClientProviderImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserToken loginToUser() {
        User user = User.builder()
                .username("will.y1@hotmail.com")
                .password("Fendrox15")
                .build();
        UserToken userToken = restTemplate.postForObject("https://api.finerio.mx/api/login",user,UserToken.class);
        log.info("UserToken {}", userToken);
        return userToken;
    }
}
