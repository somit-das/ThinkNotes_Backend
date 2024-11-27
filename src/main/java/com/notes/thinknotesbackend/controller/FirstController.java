package com.notes.thinknotesbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping("/hi")
    public ResponseEntity<String> hiMessage(){
        return ResponseEntity.ok("HI");
    }
}
