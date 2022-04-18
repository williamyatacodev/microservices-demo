package com.william.yataco.testservice.application.handler;

import com.william.yataco.testservice.application.data.MovementResponse;
import com.william.yataco.testservice.application.mapper.DemoMapper;
import com.william.yataco.testservice.domain.port.api.DemoServicePort;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class DemoHandler {

    private final DemoMapper demoMapper;
    private final DemoServicePort demoServicePort;

    public DemoHandler(DemoMapper demoMapper, DemoServicePort demoServicePort) {
        this.demoMapper = demoMapper;
        this.demoServicePort = demoServicePort;
    }

    public List<MovementResponse> processEvent(String userName, String password){
        log.info("runtTest()");
        return demoServicePort.processEvent(demoMapper.mapper(userName,password));
    }
}
