package com.example.telegrambotwithchatgpt.service.impl;

import com.example.telegrambotwithchatgpt.dto.request.UserRequestDto;
import com.example.telegrambotwithchatgpt.dto.response.UserResponseDto;
import com.example.telegrambotwithchatgpt.entity.User;
import com.example.telegrambotwithchatgpt.mapper.UserMapper;
import com.example.telegrambotwithchatgpt.service.AuthenticationService;
import com.example.telegrambotwithchatgpt.service.RoleService;
import com.example.telegrambotwithchatgpt.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final UserMapper userMapper = UserMapper.INSTANCE;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public UserResponseDto register(UserRequestDto userRequestDto) {
        if (!StringUtils.hasText(userRequestDto.getUsername()) || !StringUtils.hasText(userRequestDto.getPassword())) {
            throw new IllegalArgumentException("Username and password should be not empty");
        }

        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setRoles(Set.of(roleService.getRoleByRoleName(userRequestDto.getRole())));
        return userMapper.mapToUserResponseDto(userService.save(user));
    }
}
