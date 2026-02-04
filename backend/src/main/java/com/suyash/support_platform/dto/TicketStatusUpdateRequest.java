package com.suyash.support_platform.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TicketStatusUpdateRequest {

    @NotBlank
    private String status;
}
