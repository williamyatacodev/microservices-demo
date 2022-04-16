package com.william.yataco.testservice.domain.port.api;

import com.william.yataco.testservice.application.data.MovementResponse;
import com.william.yataco.testservice.application.data.UserRequest;

import java.util.List;

public interface DemoServicePort {

     List<MovementResponse> processEvent(UserRequest userRequest);
}
