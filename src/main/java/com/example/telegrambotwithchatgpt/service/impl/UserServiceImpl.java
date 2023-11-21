package com.example.telegrambotwithchatgpt.service.impl;

import com.example.telegrambotwithchatgpt.dto.response.UserResponseDto;
import com.example.telegrambotwithchatgpt.entity.User;
import com.example.telegrambotwithchatgpt.mapper.UserMapper;
import com.example.telegrambotwithchatgpt.repository.UserRepository;
import com.example.telegrambotwithchatgpt.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private static final UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToUserResponseDto)
                .collect(Collectors.toList());
    }
}
