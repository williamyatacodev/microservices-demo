package com.william.yataco.testservice.application.common;

import com.william.yataco.testservice.application.data.MovementResponse;

import java.util.List;

public class Util {

    public static List<MovementResponse> buildListMovement(){
        return List.of(MovementResponse.builder().build(),MovementResponse.builder().build());
    }
}
