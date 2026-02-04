package com.suyash.support_platform.service;

import com.suyash.support_platform.dto.TicketCreateRequest;
import com.suyash.support_platform.model.Ticket;
import com.suyash.support_platform.model.TicketComment;
import com.suyash.support_platform.repository.TicketCommentRepository;
import com.suyash.support_platform.repository.TicketRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository repo;
    private final TicketCommentRepository commentRepo;

    private String id;


    public TicketService(TicketRepository repo,TicketCommentRepository commentRepo){
        this.repo=repo;
        this.commentRepo=commentRepo;
    }

    public Ticket create(TicketCreateRequest req){
            Ticket t = new Ticket();
            t.setTitle(req.getTitle());
            t.setDescription(req.getDescription());
            t.setPriority(req.getPriority());
            t.setMerchantEmail(req.getMerchantEmail());
            t.setStatus("OPEN");
            t.setCreatedAt(LocalDateTime.now());

            return repo.save(t);
    }

    public List<Ticket> getAll(){
        return repo.findAll();
    }

    public Ticket getById(String id){
        this.id = id;
        return repo.findById(id)
                .orElseThrow(()-> new RuntimeException("Ticket not found"));
    }

    public void delete(String id){
        repo.deleteById(id);
    }

    public Ticket updateStatus(String id,String status){
        Ticket t=getById(id);
        t.setStatus(status);
        return repo.save(t);
    }


    public TicketComment addComment(String ticketId,String msg,String author){
        TicketComment c=new TicketComment();
        c.setTicketId(ticketId);
        c.setMessage(msg);
        c.setAuthor(author);
        c.setCreatedAt(LocalDateTime.now());

        return commentRepo.save(c);
    }

    public List<TicketComment> getComments(String ticketId){
        return commentRepo.findByTicketId(ticketId);
    }

    public List<Ticket> byStatus(String status){
        return repo.findByStatus(status);
    }

    public List<Ticket> byPriority(String priority){
        return repo.findByPriority(priority);
    }
    public List<Ticket> search(String q){
        return repo.findByTitleContainingIgnoreCase(q);
    }

    public Ticket assign(String id , String agent){
        Ticket t = repo.findById(id).orElseThrow(() ->
                new RuntimeException("Ticket not found"));
        t.setAssignedTo(agent);
        return repo.save(t);
    }
    public Page<Ticket> getPaged(int page, int size) {

        return repo.findAll(
                PageRequest.of(
                        page,
                        size,
                        Sort.by("createdAt").descending()
                )
        );
    }

    public List<Ticket> myTickets(String agent) {
        return repo.findByAssignedTo(agent);
    }

}
