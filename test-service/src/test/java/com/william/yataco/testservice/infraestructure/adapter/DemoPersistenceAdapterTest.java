package com.william.yataco.testservice.infraestructure.adapter;

import com.william.yataco.testservice.domain.common.Util;
import com.william.yataco.testservice.domain.model.Movement;
import com.william.yataco.testservice.infraestructure.provider.database.h2.MovementEntityRepository;
import com.william.yataco.testservice.infraestructure.provider.database.model.MovementEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class DemoPersistenceAdapterTest {

    @Autowired
    private MovementEntityRepository movementEntityRepository;

    @InjectMocks
    private DemoPersistenceAdapter demoPersistenceAdapter;

    @BeforeEach
    void beforeInit() {
        demoPersistenceAdapter = new DemoPersistenceAdapter(movementEntityRepository);
    }

    @Test
    void saveMovements_whenHaveMovements_thenOk() {

        List<Movement> movementList = Util.buildListMovement();
        demoPersistenceAdapter.saveMovements(movementList);
        int listMovement = movementEntityRepository.findAll().size();
        assertEquals(2, listMovement);
    }

    @Test
    void getMovements_whenHaveMovements_thenOk() {

        MovementEntity movementEntity = new MovementEntity();
        movementEntity.setId(UUID.randomUUID().toString());
        List<MovementEntity> movementEntityList = Stream.of(movementEntity).collect(Collectors.toList());
        movementEntityRepository.saveAllAndFlush(movementEntityList);
        int listMovement = demoPersistenceAdapter.getMovements().size();
        assertEquals(1, listMovement);
    }
}
