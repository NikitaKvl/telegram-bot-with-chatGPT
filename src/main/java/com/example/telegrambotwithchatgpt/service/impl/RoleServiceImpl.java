package com.example.telegrambotwithchatgpt.service.impl;

import com.example.telegrambotwithchatgpt.entity.Role;
import com.example.telegrambotwithchatgpt.exception.RoleNotFoundException;
import com.example.telegrambotwithchatgpt.repository.RoleRepository;
import com.example.telegrambotwithchatgpt.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleRepository.getRoleByRoleName(Role.RoleName.valueOf(roleName)).orElseThrow(
                () -> new RoleNotFoundException("Can't find role " + roleName));
    }

    @Override
    public void createDefaultRoles() {
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleRepository.save(userRole);
        roleRepository.save(adminRole);
    }
}
