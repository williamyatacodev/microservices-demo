package com.william.yataco.testservice.application.controller;

import com.william.yataco.testservice.application.data.MovementResponse;
import com.william.yataco.testservice.application.handler.DemoHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/runTest")
public class DemoController {

    private final DemoHandler demoHandler;

    public DemoController(DemoHandler demoHandler) {
        this.demoHandler = demoHandler;
    }

    @GetMapping
    public ResponseEntity<List<MovementResponse>> runtTest(@RequestParam String username, @RequestParam String password){
        List<MovementResponse> movementList = demoHandler.processEvent(username,password);
        return ResponseEntity.ok(movementList);
    }
}
