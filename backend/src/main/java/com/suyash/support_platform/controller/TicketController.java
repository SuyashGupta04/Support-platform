package com.suyash.support_platform.controller;

import com.suyash.support_platform.dto.CommentCreateRequest;
import com.suyash.support_platform.dto.TicketCreateRequest;
import com.suyash.support_platform.dto.TicketStatusUpdateRequest;
import com.suyash.support_platform.model.Ticket;
import com.suyash.support_platform.model.TicketComment;
import com.suyash.support_platform.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService service;

    public TicketController(TicketService service){
        this.service=service;
    }

    @PostMapping
    public Ticket create(@Valid @RequestBody TicketCreateRequest req){
        return service.create(req);
    }

    @GetMapping
    public List<Ticket> list(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Ticket get(@PathVariable String id){
            return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        service.delete(id);
    }

    @PutMapping("/{id}/status")
    public Ticket updateStatus(@PathVariable String id, @RequestBody TicketStatusUpdateRequest req){
        return service.updateStatus(id,req.getStatus());
    }

    @PostMapping("/{id}/comments")
    public TicketComment addComment(@PathVariable String id, @RequestBody CommentCreateRequest req){
        return service.addComment(id,req.getMessage(),req.getAuthor());
    }

    @GetMapping("/{id}/comments")
    public List<TicketComment> comments(@PathVariable String id){
        return service.getComments(id);
    }

    @GetMapping("/status/{status}")
    public List<Ticket> byStatus(@PathVariable String status){
        return service.byStatus(status);
    }

    @GetMapping("/priority/{priority}")
    public List<Ticket> byPriority(@PathVariable String priority){
        return service.byPriority(priority);
    }

    @GetMapping("/search")
    public List<Ticket> search(@RequestParam String q){
        return service.search(q);
    }

    @PutMapping("/{id}/assign")
    public Ticket assign(@PathVariable String id,@RequestParam String agent){
        return service.assign(id,agent);
    }

    @GetMapping("/paged")
    public Page<Ticket> paged(
            @RequestParam int page,
            @RequestParam int size) {

        return service.getPaged(page, size);
    }

    @GetMapping("/my")
    public List<Ticket> myAssigned() {

        String username =
                Objects.requireNonNull(SecurityContextHolder.getContext()
                                .getAuthentication())
                        .getName();

        return service.myTickets(username);
    }

}
