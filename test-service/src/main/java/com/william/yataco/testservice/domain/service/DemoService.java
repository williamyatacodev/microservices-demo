package com.william.yataco.testservice.domain.service;

import com.william.yataco.testservice.application.data.MovementResponse;
import com.william.yataco.testservice.application.data.UserRequest;
import com.william.yataco.testservice.domain.handler.DemoTemplateException;
import com.william.yataco.testservice.domain.model.Movement;
import com.william.yataco.testservice.domain.model.User;
import com.william.yataco.testservice.domain.port.api.DemoServicePort;
import com.william.yataco.testservice.domain.port.spi.DemoIntegrationPort;
import com.william.yataco.testservice.domain.port.spi.DemoPersistencePort;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Log4j2
@Service
public class DemoService implements DemoServicePort {

    private final DemoIntegrationPort demoIntegrationPort;
    private final DemoPersistencePort demoPersistencePort;

    public DemoService(DemoIntegrationPort demoIntegrationPort, DemoPersistencePort demoPersistencePort) {
        this.demoIntegrationPort = demoIntegrationPort;
        this.demoPersistencePort = demoPersistencePort;
    }
    @Override
    public List<MovementResponse> processEvent(UserRequest userRequest) {
        log.info("processEvent()");
        try {
            //Login
            demoIntegrationPort.loginToUser(userRequest);
            //Me
            User user = demoIntegrationPort.getInfoUser();
            //Save first 10 movements
            this.saveMovements(user.getId(),0);
            //Save next 10 movements
            this.saveMovements(user.getId(),10);
            //Get Movements from Database
            List<Movement> movementList = demoPersistencePort.getMovements();
            return movementList.stream().map(movement -> MovementResponse.builder()
                            .id(movement.getId())
                            .amount(movement.getAmount())
                            .dateCreated(movement.getDateCreated())
                            .description(movement.getDescription())
                            .type(movement.getType())
                            .build())
                    .toList();
        }catch (DemoTemplateException e){
            throw new ResponseStatusException(
                    e.getStatusCode(), e.getError(),e.getCause());
        }
    }

    private void saveMovements(String identifier, int offSet){
        log.info("saveMovements()");
        //Movements
        List<Movement> movements = demoIntegrationPort.getMovements(identifier,offSet);
        //Save Movements
        demoPersistencePort.saveMovements(movements);
    }
}
