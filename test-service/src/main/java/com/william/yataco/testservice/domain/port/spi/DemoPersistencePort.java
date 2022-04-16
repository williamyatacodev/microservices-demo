package com.william.yataco.testservice.domain.port.spi;

import com.william.yataco.testservice.domain.model.Movement;

import java.util.List;

public interface DemoPersistencePort {

    void saveMovements(List<Movement> movementList);

    List<Movement> getMovements();
}
