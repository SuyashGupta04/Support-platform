package com.suyash.support_platform.controller;

import com.suyash.support_platform.dto.AdminAnalyticsDTO;
import com.suyash.support_platform.model.User;
import com.suyash.support_platform.repository.TicketRepository;
import com.suyash.support_platform.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserRepository repo;

    public AdminController(UserRepository repo) {
        this.repo = repo;
    }

    // list all users
    @GetMapping("/users")
    public List<User> users() {
        return repo.findAll();
    }

    // change role
    @PutMapping("/users/{id}/role")
    public User changeRole(
            @PathVariable String id,
            @RequestParam String role) {

        User u = repo.findById(id).orElseThrow();
        u.setRole(role);
        return repo.save(u);
    }

    @GetMapping("/stats")
    public AdminAnalyticsDTO stats(
            TicketRepository ticketRepo,
            UserRepository userRepo) {

        return new AdminAnalyticsDTO(

                ticketRepo.count(),

                ticketRepo.countByStatus("OPEN"),
                ticketRepo.countByStatus("IN_PROGRESS"),
                ticketRepo.countByStatus("RESOLVED"),

                ticketRepo.countByPriority("HIGH"),
                ticketRepo.countByPriority("MEDIUM"),
                ticketRepo.countByPriority("LOW"),

                ticketRepo.countByAssignedToIsNotNull(),
                ticketRepo.countByAssignedToIsNull(),

                userRepo.count()
        );
    }


}
