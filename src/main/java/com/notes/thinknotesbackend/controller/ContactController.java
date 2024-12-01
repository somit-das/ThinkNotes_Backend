package com.notes.thinknotesbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/public/contact")
public class ContactController {

    @GetMapping
    public ResponseEntity<?> getContacts(){
        Map<String,String> map = new HashMap<>();
        map.put("email","smiath@gmail.com");
        map.put("address","At/po - Chhatia, Dist - Jajpur , State - Odisha");
        map.put("phonenum","+91 9494994949");
        return ResponseEntity.ok().body(map);
    }

}
