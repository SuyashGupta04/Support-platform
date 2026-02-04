package com.suyash.support_platform.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "ticket_comments")
public class TicketComment {

    @Id
    private String id;

    private String ticketId;
    private String message;
    private String author;
    private LocalDateTime createdAt;
}
