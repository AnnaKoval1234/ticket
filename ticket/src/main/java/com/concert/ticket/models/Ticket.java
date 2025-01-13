package com.concert.ticket.models;

import jakarta.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name ="participant_id", nullable = false)
    private Participant participant;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }

    public Participant getParticipant() {
        return participant;
    }
    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Ticket() {
    }
}
