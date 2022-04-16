package com.william.yataco.testservice.infraestructure.adapter;

import com.william.yataco.testservice.domain.model.Movement;
import com.william.yataco.testservice.domain.port.spi.DemoPersistencePort;
import com.william.yataco.testservice.infraestructure.provider.database.h2.MovementEntityRepository;
import com.william.yataco.testservice.infraestructure.provider.database.model.MovementEntity;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DemoPersistenceAdapter implements DemoPersistencePort {

    private final MovementEntityRepository movementEntityRepository;

    public DemoPersistenceAdapter(MovementEntityRepository movementEntityRepository) {
        this.movementEntityRepository = movementEntityRepository;
    }

    @Override
    public void saveMovements(List<Movement> movementList) {
        List<MovementEntity> movementEntityList = movementList.stream().map(movement -> {
            MovementEntity movementEntity = new MovementEntity();
            movementEntity.setId(movement.getId());
            movementEntity.setAmount(movement.getAmount());
            movementEntity.setDateCreated(movement.getDateCreated());
            movementEntity.setDescription(movement.getDescription());
            movementEntity.setType(movement.getType());
            movementEntity.setLastUpdated(OffsetDateTime.now());
            return movementEntity;
        }).collect(Collectors.toList());
        movementEntityRepository.saveAllAndFlush(movementEntityList);
    }

    @Override
    public List<Movement> getMovements() {
        return movementEntityRepository.findAll().stream()
                .map(movementEntity -> Movement.builder()
                        .id(String.valueOf(movementEntity.getId()))
                        .amount(movementEntity.getAmount())
                        .dateCreated(movementEntity.getDateCreated())
                        .description(movementEntity.getDescription())
                        .type(movementEntity.getType())
                        .build()).collect(Collectors.toList());
    }
}
