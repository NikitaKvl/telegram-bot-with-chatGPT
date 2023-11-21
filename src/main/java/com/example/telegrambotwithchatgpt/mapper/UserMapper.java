package com.example.telegrambotwithchatgpt.mapper;

import com.example.telegrambotwithchatgpt.dto.response.UserResponseDto;
import com.example.telegrambotwithchatgpt.entity.Role;
import com.example.telegrambotwithchatgpt.entity.User;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "roles", target = "role", qualifiedByName = "toRole")
    UserResponseDto mapToUserResponseDto(User user);

    @Named("toRole")
    static String setRolesToRole(Set<Role> roleSet) {
        return roleSet.iterator().next().toString();
    }
}
