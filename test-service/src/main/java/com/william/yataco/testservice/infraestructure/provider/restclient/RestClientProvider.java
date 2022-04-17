package com.william.yataco.testservice.infraestructure.provider.restclient;

import com.william.yataco.testservice.application.data.UserRequest;
import com.william.yataco.testservice.domain.model.Movements;
import com.william.yataco.testservice.domain.model.User;
import com.william.yataco.testservice.domain.model.UserToken;

public interface RestClientProvider {

    UserToken loginToUser(UserRequest userRequest);

    User getInfoUser(String authorization);

    Movements getMovements(String authorization, String identifierUser, int offSet);
}
