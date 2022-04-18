package com.william.yataco.testservice.infraestructure.adapter;

import com.william.yataco.testservice.application.data.UserRequest;
import com.william.yataco.testservice.domain.model.Movement;
import com.william.yataco.testservice.domain.model.User;
import com.william.yataco.testservice.domain.port.spi.DemoIntegrationPort;
import com.william.yataco.testservice.infraestructure.provider.restclient.RestClientProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class DemoIntegrationAdapter implements DemoIntegrationPort {

    private final RestClientProvider restClientProvider;

    public DemoIntegrationAdapter(RestClientProvider restClientProvider) {
        this.restClientProvider = restClientProvider;
    }
    @Override
    public void loginToUser(UserRequest userRequest) {
        log.info("loginToUser()");
        restClientProvider.loginToUser(userRequest);
    }
    @Override
    public User getInfoUser() {
        log.info("getInfoUser()");
        return restClientProvider.getInfoUser();
    }

    @Override
    public List<Movement> getMovements(String identifierUser, int offSet) {
        log.info("getMovements()");
        return restClientProvider.getMovements(identifierUser,offSet).getData();
    }
}
