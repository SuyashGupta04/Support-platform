package com.suyash.support_platform.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("api_logs")
@Data
public class ApiLog {

    @Id
    private String id;

    private String path;
    private String method;

    private int status;
    private long duration;

    private LocalDateTime timestamp;
}
