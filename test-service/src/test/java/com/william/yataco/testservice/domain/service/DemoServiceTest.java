package com.william.yataco.testservice.domain.service;

import com.william.yataco.testservice.application.data.MovementResponse;
import com.william.yataco.testservice.application.data.UserRequest;
import com.william.yataco.testservice.domain.common.Util;
import com.william.yataco.testservice.domain.handler.DemoTemplateException;
import com.william.yataco.testservice.domain.model.Movement;
import com.william.yataco.testservice.domain.model.User;
import com.william.yataco.testservice.domain.model.UserToken;
import com.william.yataco.testservice.domain.port.spi.DemoIntegrationPort;
import com.william.yataco.testservice.domain.port.spi.DemoPersistencePort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class DemoServiceTest {

    @MockBean
    private DemoIntegrationPort demoIntegrationPort;

    @MockBean
    private DemoPersistencePort demoPersistencePort;

    @Autowired
    private DemoService demoService;

    @Test
    void givenMovements_whenProcessEvent_thenOk() {


        UserRequest userRequest = new UserRequest();
        UserToken userToken = new UserToken();
        userToken.setAccessToken("token");
        User user = new User();
        user.setId("id");
        List<Movement> movements = Util.buildListMovement();
        given(demoIntegrationPort.loginToUser(any(UserRequest.class))).willReturn(userToken);
        given(demoIntegrationPort.getInfoUser(anyString())).willReturn(user);
        given(demoIntegrationPort.getMovements(anyString(),anyString(),anyInt())).willReturn(movements);
        doNothing().when(demoPersistencePort).saveMovements(movements);
        given(demoPersistencePort.getMovements()).willReturn(movements);

        assertEquals(2, demoService.processEvent(userRequest).size());

        verify(demoIntegrationPort).loginToUser(any(UserRequest.class));
        verify(demoIntegrationPort).getInfoUser(anyString());
        verify(demoIntegrationPort, times(2)).getMovements(anyString(),anyString(),anyInt());
        verify(demoPersistencePort, times(2)).saveMovements(movements);
        verify(demoPersistencePort).getMovements();
    }

    @Test
    void givenMovements_whenLoginGenerateError_thenThrow() {

        UserRequest userRequest = new UserRequest();
        DemoTemplateException demoTemplateException = new DemoTemplateException(HttpStatus.UNAUTHORIZED,"Error 401","");
        given(demoIntegrationPort.loginToUser(any(UserRequest.class))).willThrow(demoTemplateException);

        assertThrows(ResponseStatusException.class, () -> {
            List<MovementResponse> movementResponses = demoService.processEvent(userRequest);
        });
    }

}
