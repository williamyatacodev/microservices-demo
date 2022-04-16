package com.william.yataco.testservice.application.controller;

import com.william.yataco.testservice.application.data.MovementResponse;
import com.william.yataco.testservice.application.data.UserRequest;
import com.william.yataco.testservice.application.handler.DemoHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/runTest")
public class DemoController {

    private final DemoHandler demoHandler;

    public DemoController(DemoHandler demoHandler) {
        this.demoHandler = demoHandler;
    }


    @GetMapping()
    public ResponseEntity<List<MovementResponse>> runtTest(@RequestBody UserRequest userRequest){
        List<MovementResponse> movementList = demoHandler.processEvent(userRequest);
        return ResponseEntity.ok(movementList);
    }
}
