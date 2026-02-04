package com.suyash.support_platform.dto;

import lombok.Data;

@Data
public class CommentCreateRequest {
    private String message;
    private String author;
}
