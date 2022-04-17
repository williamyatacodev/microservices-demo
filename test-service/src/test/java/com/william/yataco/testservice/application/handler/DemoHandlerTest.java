package com.william.yataco.testservice.application.handler;

import com.william.yataco.testservice.application.common.Util;
import com.william.yataco.testservice.application.data.MovementResponse;
import com.william.yataco.testservice.application.data.UserRequest;
import com.william.yataco.testservice.domain.port.api.DemoServicePort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
class DemoHandlerTest {

    @MockBean
    private DemoServicePort demoServicePort;

    @Autowired
    private DemoHandler demoHandler;


    @Test
    void givenMovements_whenProcessEvent_thenOk() {

        List<MovementResponse> movementReturn = Util.buildListMovement();

        given(demoServicePort.processEvent(any(UserRequest.class))).willReturn(movementReturn);

        assertEquals(2, demoHandler.processEvent("test","test").size());

        verify(demoServicePort).processEvent(any(UserRequest.class));
    }


}
