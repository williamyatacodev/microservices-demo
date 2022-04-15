package com.william.yataco.testservice.domain.port.spi;

import com.william.yataco.testservice.domain.model.UserToken;

public interface DemoIntegrationPort {

    UserToken loginToUser();
}
