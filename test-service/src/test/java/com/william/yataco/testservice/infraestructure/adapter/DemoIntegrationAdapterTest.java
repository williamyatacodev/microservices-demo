package com.william.yataco.testservice.infraestructure.adapter;

import com.william.yataco.testservice.application.data.UserRequest;
import com.william.yataco.testservice.domain.common.Util;
import com.william.yataco.testservice.domain.model.Movement;
import com.william.yataco.testservice.domain.model.Movements;
import com.william.yataco.testservice.domain.model.User;
import com.william.yataco.testservice.domain.model.UserToken;
import com.william.yataco.testservice.infraestructure.provider.restclient.RestClientProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
class DemoIntegrationAdapterTest {

    @MockBean
    private RestClientProvider restClientProvider;

    @Autowired
    private DemoIntegrationAdapter demoIntegrationAdapter;

    @Test
    void loginToUser_whenLoginToUserRequest_thenOk() {

        UserRequest userRequest = new UserRequest();

        doNothing().when(restClientProvider).loginToUser(any(UserRequest.class));

        demoIntegrationAdapter.loginToUser(userRequest);

        verify(restClientProvider).loginToUser(any(UserRequest.class));
    }

    @Test
    void getInfoUser_whenToken_thenOk() {

        User user = new User();
        given(restClientProvider.getInfoUser()).willReturn(user);

        assertNotNull(demoIntegrationAdapter.getInfoUser());

        verify(restClientProvider).getInfoUser();
    }

    @Test
    void getMovements_whenLoginSuccess_thenOk() {

        Movements movements = new Movements();
        List<Movement> movementListMock = Util.buildListMovement();
        movements.setData(movementListMock);
        given(restClientProvider.getMovements(anyString(),anyInt())).willReturn(movements);

        assertNotNull(demoIntegrationAdapter.getMovements("id",0));

        verify(restClientProvider).getMovements(anyString(),anyInt());
    }
}
