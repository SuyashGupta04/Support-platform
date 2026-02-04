package com.suyash.support_platform.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "tickets")
public class Ticket {

    @Id
    private String id;

    private String title;
    private String description;

    @Indexed
    private String status;

    @Indexed
    private String priority;

    private String merchantEmail;

    private LocalDateTime createdAt;
    private String assignedTo;


}
