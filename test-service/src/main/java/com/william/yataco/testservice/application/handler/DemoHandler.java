package com.william.yataco.testservice.application.handler;

import com.william.yataco.testservice.domain.port.api.DemoServicePort;
import org.springframework.stereotype.Component;

@Component
public class DemoHandler {

    private final DemoServicePort demoServicePort;

    public DemoHandler(DemoServicePort demoServicePort) {
        this.demoServicePort = demoServicePort;
    }

    public String processEvent(){
        return demoServicePort.processEvent();
    }
}
