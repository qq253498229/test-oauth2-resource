package com.example.client.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author wangbin
 */
@RestController
public class TestController {

    @GetMapping("/public")
    public ResponseEntity publicMethod() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("data", "publicMethod");
        return ok(map);
    }

    @GetMapping("/private")
    public ResponseEntity privateMethod() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("data", "privateMethod");
        return ok(map);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/user")
    public ResponseEntity userMethod() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("data", "userMethod");
        return ok(map);
    }

    @PreAuthorize("hasAnyRole('VIP')")
    @GetMapping("/vip")
    public ResponseEntity vipMethod() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("data", "vipMethod");
        return ok(map);
    }
}
