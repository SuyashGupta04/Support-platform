package com.suyash.support_platform.controller;

import com.suyash.support_platform.dto.ApiReplayRequest;
import com.suyash.support_platform.service.DebugService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/debug")
public class DebugController {

    private final DebugService service;

    public DebugController(DebugService service){
        this.service=service;
    }

    @PostMapping("/replay")
    public ResponseEntity<String> replay(@RequestBody ApiReplayRequest req){
            return service.replay(req.getUrl(), req.getMethod(), req.getMethod());
    }

    @GetMapping("/time")
    public long time(@RequestParam String url){
            return service.responseTime(url);
    }

    @PostMapping("/json/validate")
    public String validate(@RequestBody String json){
        try{
            new ObjectMapper().readTree(json);
            return "VALID JSON";
        }
        catch (Exception e){
            return "INVALID JSON" + e.getMessage();
        }
    }

}
