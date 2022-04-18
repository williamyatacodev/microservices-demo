package com.william.yataco.testservice.domain.port.spi;

import com.william.yataco.testservice.application.data.UserRequest;
import com.william.yataco.testservice.domain.model.Movement;
import com.william.yataco.testservice.domain.model.User;

import java.util.List;

public interface DemoIntegrationPort {

    void loginToUser(UserRequest userRequest);

    User getInfoUser();

    List<Movement> getMovements(String identifierUser, int offSet);
}
