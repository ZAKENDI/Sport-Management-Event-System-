package com.example.myproj.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sport_event") // Указываем таблицу в базе данных
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Используем IDENTITY, чтобы соответствовать автоинкременту в MySQL
    private Long id;

    @Column(name = "event_name", nullable = false) // Поле event_name
    private String eventName;

    @Column(name = "creater_fullname", nullable = false) // Поле creater_fullname
    private String createrFullname;

    @Column(name = "event_date", nullable = false) // Поле event_date
    private LocalDate eventDate;

    @Column(name = "sport_type", nullable = false) // Поле sport_type
    private String sportType;

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCreaterFullname() {
        return createrFullname;
    }

    public void setCreaterFullname(String createrFullname) {
        this.createrFullname = createrFullname;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }
}