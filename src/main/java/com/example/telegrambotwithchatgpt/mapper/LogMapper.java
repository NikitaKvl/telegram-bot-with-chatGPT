package com.example.telegrambotwithchatgpt.mapper;

import com.example.telegrambotwithchatgpt.dto.request.LogRequestDto;
import com.example.telegrambotwithchatgpt.dto.response.LogResponseDto;
import com.example.telegrambotwithchatgpt.entity.Log;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LogMapper {
    LogMapper INSTANCE = Mappers.getMapper(LogMapper.class);

    LogResponseDto mapToLogResponseDto(Log log);

    Log mapToLog(LogRequestDto logRequestDto);
}
