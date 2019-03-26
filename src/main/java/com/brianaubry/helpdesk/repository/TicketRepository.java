package com.brianaubry.helpdesk.repository;

import com.brianaubry.helpdesk.model.Ticket;
import com.brianaubry.helpdesk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Ticket findById(int id);

    List<Ticket> findByAssignedToId(int id);
}
