package com.william.yataco.testservice.application.controller;

import com.william.yataco.testservice.application.handler.DemoHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/runTest")
public class DemoController {

    private final DemoHandler demoHandler;

    public DemoController(DemoHandler demoHandler) {
        this.demoHandler = demoHandler;
    }


    @GetMapping("/demo")
    public ResponseEntity<String> runtTest(){
        String token = demoHandler.processEvent();
        return ResponseEntity.ok(token);
    }
}
