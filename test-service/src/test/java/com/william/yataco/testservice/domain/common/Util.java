package com.william.yataco.testservice.domain.common;

import com.william.yataco.testservice.domain.model.Movement;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {

    public static List<Movement> buildListMovement(){
        return Stream.of(Movement.builder().id(UUID.randomUUID().toString()).build(),
                Movement.builder().id(UUID.randomUUID().toString()).build()).collect(Collectors.toList());
    }
}
