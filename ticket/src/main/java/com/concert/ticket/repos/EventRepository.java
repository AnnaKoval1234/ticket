package com.concert.ticket.repos;
import org.springframework.data.repository.CrudRepository;

import com.concert.ticket.models.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

}
