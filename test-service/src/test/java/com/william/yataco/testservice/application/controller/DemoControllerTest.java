package com.william.yataco.testservice.application.controller;

import com.william.yataco.testservice.application.common.Util;
import com.william.yataco.testservice.application.data.MovementResponse;
import com.william.yataco.testservice.application.handler.DemoHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DemoHandler demoHandler;

    @Test
    void givenMovements_whenSendWithBasicAuthorizationAndWithParameters_thenStatus200()
            throws Exception {

        List<MovementResponse> movementResponses = Util.buildListMovement();

        given(demoHandler.processEvent(anyString(),anyString())).willReturn(movementResponses);

        mockMvc.perform(get("/runTest?username=demo&password=demo")
                        .contentType(MediaType.APPLICATION_JSON).with(httpBasic("demo","demo")))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2));

        verify(demoHandler).processEvent(anyString(),anyString());
    }

    @Test
    void givenMovements_whenSendWithBasicAuthorizationAndWithoutParameters_thenStatus400()
            throws Exception {

        mockMvc.perform(get("/runTest")
                        .contentType(MediaType.APPLICATION_JSON).with(httpBasic("demo","demo")))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void givenMovements_whenSendWithoutBasicAuthorization_thenStatus401()
            throws Exception {

        mockMvc.perform(get("/runTest")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}
