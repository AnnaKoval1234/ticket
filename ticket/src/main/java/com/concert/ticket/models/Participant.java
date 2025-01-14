package com.concert.ticket.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Имя не должно быть пустым!")
    @Pattern(regexp = "^[а-яА-Я]+$",
            message = "В Имени могут быть только буквы!")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "Фамилия не должна быть пустой!")
    @Pattern(regexp = "^[а-яА-Я]+$",
            message = "В Фамилии могут быть только буквы!")
    @Column(nullable = false)
    private String surname;

    @NotEmpty(message = "Номер телефона не должен быть пустым!")
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            message = "В Номере телефона могут быть указаны только мобильный или городской номера!")
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

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

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Participant() {
    }

    public Participant(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return surname + " " + name;
    }
}
