package com.kh.demoapi.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/fake")
@RestController
public class APIController {

    @PostMapping("/api")
    public ResponseEntity<Map<String, Object>> api(@RequestBody Map<String, Object> info) {
        System.out.println("api 호출됨 " + info);
        return ResponseEntity.ok(info);
//        return ResponseEntity.badRequest().build();
    }
}
