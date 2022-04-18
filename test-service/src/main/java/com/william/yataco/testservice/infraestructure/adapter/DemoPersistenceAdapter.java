package com.william.yataco.testservice.infraestructure.adapter;

import com.william.yataco.testservice.domain.model.Movement;
import com.william.yataco.testservice.domain.port.spi.DemoPersistencePort;
import com.william.yataco.testservice.infraestructure.provider.database.h2.MovementEntityRepository;
import com.william.yataco.testservice.infraestructure.provider.database.model.MovementEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Log4j2
@Component
public class DemoPersistenceAdapter implements DemoPersistencePort {

    private final MovementEntityRepository movementEntityRepository;

    public DemoPersistenceAdapter(MovementEntityRepository movementEntityRepository) {
        this.movementEntityRepository = movementEntityRepository;
    }

    @Override
    public void saveMovements(List<Movement> movementList) {
        log.info("saveMovements()");
        List<MovementEntity> movementEntityList = movementList.stream().map(movement -> {
            MovementEntity movementEntity = new MovementEntity();
            movementEntity.setId(movement.getId());
            movementEntity.setAmount(movement.getAmount());
            movementEntity.setDateCreated(movement.getDateCreated());
            movementEntity.setDescription(movement.getCustomDescription());
            movementEntity.setType(movement.getType());
            movementEntity.setLastUpdated(OffsetDateTime.now());
            return movementEntity;
        }).toList();
        movementEntityRepository.saveAllAndFlush(movementEntityList);
    }

    @Override
    public List<Movement> getMovements() {
        log.info("getMovements()");
        return movementEntityRepository.findAll().stream()
                .map(movementEntity -> Movement.builder()
                        .id(String.valueOf(movementEntity.getId()))
                        .amount(movementEntity.getAmount())
                        .dateCreated(movementEntity.getDateCreated())
                        .description(movementEntity.getDescription())
                        .type(movementEntity.getType())
                        .build()).toList();
    }
}
