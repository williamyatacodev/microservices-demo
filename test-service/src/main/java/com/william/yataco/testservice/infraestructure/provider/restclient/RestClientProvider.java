package com.william.yataco.testservice.infraestructure.provider.restclient;

import com.william.yataco.testservice.domain.model.UserToken;

public interface RestClientProvider {

    UserToken loginToUser();
}
