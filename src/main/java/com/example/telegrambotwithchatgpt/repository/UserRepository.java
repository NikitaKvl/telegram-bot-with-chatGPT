package com.example.telegrambotwithchatgpt.repository;

import com.example.telegrambotwithchatgpt.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("from users u JOIN fetch u.roles where u.username = ?1")
    Optional<User> findByUsername(String username);
}
