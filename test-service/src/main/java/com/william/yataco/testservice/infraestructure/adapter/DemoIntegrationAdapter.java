package com.william.yataco.testservice.infraestructure.adapter;

import com.william.yataco.testservice.domain.model.UserToken;
import com.william.yataco.testservice.domain.port.spi.DemoIntegrationPort;
import com.william.yataco.testservice.infraestructure.provider.restclient.RestClientProvider;
import org.springframework.stereotype.Component;

@Component
public class DemoIntegrationAdapter implements DemoIntegrationPort {

    private final RestClientProvider restClientProvider;

    public DemoIntegrationAdapter(RestClientProvider restClientProvider) {
        this.restClientProvider = restClientProvider;
    }

    @Override
    public UserToken loginToUser() {
        return restClientProvider.loginToUser();
    }
}
