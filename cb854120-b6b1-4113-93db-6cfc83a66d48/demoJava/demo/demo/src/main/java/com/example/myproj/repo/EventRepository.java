package com.example.myproj.repo;

import com.example.myproj.models.Event;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {

    // Найти события по типу спорта
    List<Event> findBySportType(String sportType);

    // Найти события по дате
    List<Event> findByEventDate(LocalDate eventDate);

    // Найти события, содержащие определенное имя в названии
    List<Event> findByEventNameContaining(String keyword);

    // Найти события по создателю
    List<Event> findByCreaterFullname(String createrFullname);
}