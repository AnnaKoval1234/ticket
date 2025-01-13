package com.concert.ticket.repos;
import org.springframework.data.repository.CrudRepository;

import com.concert.ticket.models.Place;

public interface PlaceRepository extends CrudRepository<Place, Long> {

}
