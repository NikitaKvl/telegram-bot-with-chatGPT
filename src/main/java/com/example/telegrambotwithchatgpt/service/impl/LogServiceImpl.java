package com.example.telegrambotwithchatgpt.service.impl;

import com.example.telegrambotwithchatgpt.dto.request.LogRequestDto;
import com.example.telegrambotwithchatgpt.dto.response.LogResponseDto;
import com.example.telegrambotwithchatgpt.mapper.LogMapper;
import com.example.telegrambotwithchatgpt.repository.LogRepository;
import com.example.telegrambotwithchatgpt.service.LogService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    private final LogMapper logMapper = LogMapper.INSTANCE;
    private final LogRepository logRepository;

    @Override
    public LogResponseDto save(LogRequestDto logRequestDto) {
        return logMapper.mapToLogResponseDto(logRepository.save(logMapper.mapToLog(logRequestDto)));
    }

    @Override
    public List<LogResponseDto> getAll() {
        return logRepository.findAll()
                .stream()
                .map(logMapper::mapToLogResponseDto)
                .collect(Collectors.toList());
    }
}
