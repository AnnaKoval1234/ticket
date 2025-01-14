package com.concert.ticket.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Наименование не должно быть пустым!")
    @Pattern(regexp = "^[ёЁa-zA-Zа-яА-Я .,)(»«—\"-]+$",
             message = "В Наименовании могут быть только буквы, знаки препинания и пробел!")
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = "Адрес не должен быть пустым!")
    @Pattern(regexp = "^(ул. )[ёЁа-яА-Я0-9 ]+(, )[0-9]{1,3}[а-яА-я]?$",
             message = "Адрес должен быть записан по шаблону «ул. Незванная, 123»!")
    private String address;

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

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Place() {
    }

    public Place(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return name;
    }
}
