package com.suyash.support_platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminAnalyticsDTO {

    private long totalTickets;
    private long open;
    private long inProgress;
    private long resolved;

    private long high;
    private long medium;
    private long low;

    private long assigned;
    private long unassigned;

    private long users;
}
