package com.concert.ticket.repos;
import org.springframework.data.repository.CrudRepository;

import com.concert.ticket.models.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

}
