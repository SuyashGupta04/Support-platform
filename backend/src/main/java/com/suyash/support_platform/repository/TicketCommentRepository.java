package com.suyash.support_platform.repository;

import com.suyash.support_platform.model.TicketComment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TicketCommentRepository extends MongoRepository<TicketComment,String> {
    List<TicketComment> findByTicketId(String ticketId);
}
