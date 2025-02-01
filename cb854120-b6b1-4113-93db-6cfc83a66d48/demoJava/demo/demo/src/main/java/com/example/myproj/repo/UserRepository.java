package com.example.myproj.repo;

import com.example.myproj.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Найти пользователя по nickname
    Optional<User> findByNickname(String nickname);

    // Дополнительный метод для проверки существования пользователя по nickname
    boolean existsByNickname(String nickname);
}