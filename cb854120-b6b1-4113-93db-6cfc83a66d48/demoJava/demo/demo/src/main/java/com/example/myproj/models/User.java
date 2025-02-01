package com.example.myproj.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users") // Указываем таблицу в базе данных
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Используем IDENTITY, чтобы соответствовать автоинкременту в MySQL
    private Long id;

    @Column(name = "first_name", nullable = false) // Поле first_name
    private String firstName;

    @Column(name = "last_name", nullable = false) // Поле last_name
    private String lastName;

    @Column(name = "nickname", nullable = false, unique = true) // Поле nickname
    private String nickname;

    @Column(name = "profile_picture_url") // Поле profile_picture_url
    private String profilePictureUrl;

    @Column(name = "phone_number") // Поле phone_number
    private String phoneNumber;

    @Column(name = "role", nullable = false) // Поле role
    private String role;

    @Column(name = "password", nullable = false) // Поле password
    private String password;

    @Column(name = "created_at", nullable = false) // Поле created_at
    private LocalDateTime createdAt;

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}