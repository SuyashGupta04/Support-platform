package com.suyash.support_platform.controller;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/debug")
public class MongoDebugController {

    private final MongoTemplate template;

    public MongoDebugController(MongoTemplate template) {
        this.template = template;
    }

    @GetMapping("/db")
    public String db() {
        return template.getDb().getName();
    }

    @GetMapping("/users-count")
    public long users() {
        return template.getCollection("users").countDocuments();
    }
}
