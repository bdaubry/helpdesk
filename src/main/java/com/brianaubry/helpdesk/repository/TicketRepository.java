package com.brianaubry.helpdesk.repository;

import com.brianaubry.helpdesk.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Ticket findById(int id);
}
