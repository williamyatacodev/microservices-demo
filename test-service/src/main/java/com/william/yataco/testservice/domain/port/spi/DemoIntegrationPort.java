package com.william.yataco.testservice.domain.port.spi;

import com.william.yataco.testservice.application.data.UserRequest;
import com.william.yataco.testservice.domain.model.Movement;
import com.william.yataco.testservice.domain.model.User;
import com.william.yataco.testservice.domain.model.UserToken;

import java.util.List;

public interface DemoIntegrationPort {

    UserToken loginToUser(UserRequest userRequest);

    User getInfoUser(String authorization);

    List<Movement> getMovements(String authorization, String identifierUser, int offSet);
}
