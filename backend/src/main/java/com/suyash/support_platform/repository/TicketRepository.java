package com.suyash.support_platform.repository;

import com.suyash.support_platform.model.Ticket;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TicketRepository extends MongoRepository<Ticket,String> {

    List<Ticket> findByStatus(String status);

    List<Ticket> findByPriority(String priority);

    List<Ticket> findByTitleContainingIgnoreCase(String keyword);

    Page<Ticket> findAll(@NonNull Pageable pageable);

    List<Ticket> findByAssignedTo(String assignedTo);

    void deleteById(String id);

    long countByStatus(String status);
    long countByPriority(String priority);
    long countByAssignedToIsNull();
    long countByAssignedToIsNotNull();

}
