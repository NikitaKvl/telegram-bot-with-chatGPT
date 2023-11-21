package com.example.telegrambotwithchatgpt.service;

import com.example.telegrambotwithchatgpt.entity.Role;

public interface RoleService {
    Role getRoleByRoleName(String roleName);

    void createDefaultRoles();
}
