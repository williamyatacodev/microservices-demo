package com.william.yataco.testservice.application.handler;

import com.william.yataco.testservice.application.data.MovementResponse;
import com.william.yataco.testservice.application.data.UserRequest;
import com.william.yataco.testservice.domain.port.api.DemoServicePort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DemoHandler {

    private final DemoServicePort demoServicePort;

    public DemoHandler(DemoServicePort demoServicePort) {
        this.demoServicePort = demoServicePort;
    }

    public List<MovementResponse> processEvent(UserRequest userRequest){
        return demoServicePort.processEvent(userRequest);
    }
}
