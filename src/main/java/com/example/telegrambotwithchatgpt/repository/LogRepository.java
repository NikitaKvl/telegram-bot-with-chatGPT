package com.example.telegrambotwithchatgpt.repository;

import com.example.telegrambotwithchatgpt.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
