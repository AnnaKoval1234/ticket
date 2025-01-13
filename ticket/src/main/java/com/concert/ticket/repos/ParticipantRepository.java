package com.concert.ticket.repos;
import org.springframework.data.repository.CrudRepository;

import com.concert.ticket.models.Participant;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {

}
