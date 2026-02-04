package com.suyash.support_platform.service;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DebugService {
    private final RestTemplate rest = new RestTemplate();

    public ResponseEntity<String> replay(String url,String method,String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String > entity = new HttpEntity<>(body,headers);
        return rest.exchange(url, HttpMethod.valueOf(method),entity,String.class);
    }


    public long responseTime(String url){
        long start = System.currentTimeMillis();
        rest.getForObject(url,String.class);
        return System.currentTimeMillis()-start;
    }
}
