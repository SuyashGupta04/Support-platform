package com.suyash.support_platform.controller;


import com.suyash.support_platform.model.Ticket;
import com.suyash.support_platform.repository.TicketRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final TicketRepository repo;

    public TestController(TicketRepository repo){
        this.repo=repo;
    }

    @PostMapping("/test")
    public Ticket save(){
        Ticket t= new Ticket();
        t.setTitle("Mongo Working");
        t.setStatus("OPEN");
        t.setPriority("HIGH");

        return repo.save(t);
    }

    @GetMapping("/health")
    public String health() {
        return "Support Platform Running";
    }

}
