package com.william.yataco.testservice.domain.service;

import com.william.yataco.testservice.domain.model.UserToken;
import com.william.yataco.testservice.domain.port.api.DemoServicePort;
import com.william.yataco.testservice.domain.port.spi.DemoIntegrationPort;
import org.springframework.stereotype.Service;

@Service
public class DemoService implements DemoServicePort {

    private final DemoIntegrationPort demoIntegrationPort;

    public DemoService(DemoIntegrationPort demoIntegrationPort) {
        this.demoIntegrationPort = demoIntegrationPort;
    }

    @Override
    public String processEvent() {
        UserToken userToken = demoIntegrationPort.loginToUser();
        return userToken.getAccessToken();
    }
}
