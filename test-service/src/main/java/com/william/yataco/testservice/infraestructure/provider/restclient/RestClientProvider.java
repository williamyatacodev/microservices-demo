package com.william.yataco.testservice.infraestructure.provider.restclient;

import com.william.yataco.testservice.application.data.UserRequest;
import com.william.yataco.testservice.domain.model.Movements;
import com.william.yataco.testservice.domain.model.User;

public interface RestClientProvider {

    void loginToUser(UserRequest userRequest);

    User getInfoUser();

    Movements getMovements(String identifierUser, int offSet);
}
