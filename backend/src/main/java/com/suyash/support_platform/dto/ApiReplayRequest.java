package com.suyash.support_platform.dto;

import lombok.Data;

@Data
public class ApiReplayRequest {
    private String url;
    private String method;
    private String body;
}
