package com.concert.ticket.models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Наименование не должно быть пустым!")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    @NotNull(message = "Наименование не должно быть пустым!")
    @Column(name = "date_begin", nullable = false)
    private LocalDateTime dateBegin;

    @Column(nullable = false)
    @NotNull(message = "Наименование не должно быть пустым!")
    private LocalTime duration;

    @ManyToOne
    @NotNull(message = "Наименование не должно быть пустым!")
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDateBeginString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return dateBegin.format(formatter);
    }
    public LocalDateTime getDateBegin() {
        return dateBegin;
    }
    public void setDateBegin(LocalDateTime dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getDurationString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return duration.format(formatter);
    }
    public LocalTime getDuration() {
        return duration;
    }
    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public Place getPlace() {
        return place;
    }
    public void setPlace(Place place) {
        this.place = place;
    }

    public Event() {
    }

    public Event(String name, LocalDateTime dateBegin, LocalTime duration, Place place) {
        this.name = name;
        this.dateBegin = dateBegin;
        this.duration = duration;
        this.place = place;
    }

    @Override
    public String toString() {
        return name;
    }
}
