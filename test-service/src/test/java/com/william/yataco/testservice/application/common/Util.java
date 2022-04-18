package com.william.yataco.testservice.application.common;

import com.william.yataco.testservice.application.data.MovementResponse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {

    public static List<MovementResponse> buildListMovement(){
        return Stream.of(MovementResponse.builder().build(),MovementResponse.builder().build()).collect(Collectors.toList());
    }
}
