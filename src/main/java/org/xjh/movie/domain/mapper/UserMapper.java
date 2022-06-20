package org.xjh.movie.domain.mapper;


import org.mapstruct.Mapper;
import org.xjh.movie.domain.dto.UserDto;
import org.xjh.movie.domain.model.Role;
import org.xjh.movie.domain.model.User;

import java.util.List;

/**
 * @author xjh
 * @date 6/19/2022 2:47 PM
 */
@Mapper(componentModel = "cdi")
public interface UserMapper {

    UserDto toDto(User user);

    List<UserDto> toDto(List<User> users);

    User toEntity(UserDto userDto);

    default String roleToString(Role role) {
        return role.role;
    }

    default Role stringToRole(String s) {
        return Role.findByRoleString(s);
    }
}
