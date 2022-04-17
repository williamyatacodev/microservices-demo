package com.william.yataco.testservice.infraestructure.adapter;

import com.william.yataco.testservice.application.data.UserRequest;
import com.william.yataco.testservice.domain.model.Movement;
import com.william.yataco.testservice.domain.model.User;
import com.william.yataco.testservice.domain.model.UserToken;
import com.william.yataco.testservice.domain.port.spi.DemoIntegrationPort;
import com.william.yataco.testservice.infraestructure.provider.restclient.RestClientProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DemoIntegrationAdapter implements DemoIntegrationPort {

    private final RestClientProvider restClientProvider;

    public DemoIntegrationAdapter(RestClientProvider restClientProvider) {
        this.restClientProvider = restClientProvider;
    }
    @Override
    public UserToken loginToUser(UserRequest userRequest) {
        return restClientProvider.loginToUser(userRequest);
    }
    @Override
    public User getInfoUser(String authorization) {
        return restClientProvider.getInfoUser(authorization);
    }

    @Override
    public List<Movement> getMovements(String authorization, String identifierUser, int offSet) {
        return restClientProvider.getMovements(authorization, identifierUser,offSet).getData();
    }
}
