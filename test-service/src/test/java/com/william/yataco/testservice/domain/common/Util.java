package com.william.yataco.testservice.domain.common;

import com.william.yataco.testservice.domain.model.Movement;

import java.util.List;
import java.util.UUID;

public class Util {

    public static List<Movement> buildListMovement(){
        return List.of(Movement.builder().id(UUID.randomUUID().toString()).build(),
                Movement.builder().id(UUID.randomUUID().toString()).build());
    }
}
