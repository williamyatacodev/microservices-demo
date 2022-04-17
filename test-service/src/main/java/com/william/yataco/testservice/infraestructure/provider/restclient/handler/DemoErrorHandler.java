package com.william.yataco.testservice.infraestructure.provider.restclient.handler;

import com.william.yataco.testservice.domain.common.Constant;
import com.william.yataco.testservice.domain.handler.DemoTemplateException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
@Component
public class DemoErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return new DefaultResponseErrorHandler().hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()))) {
                String httpBodyResponse = reader.lines().collect(Collectors.joining(""));
                throw new DemoTemplateException(response.getStatusCode(), Constant.ERROR_MESSAGE_CLIENT,httpBodyResponse);
            }
        }
    }
}
