package org.xjh.freemovieserver.domain.mapper;


import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import org.xjh.freemovieserver.domain.dto.UserDto;
import org.xjh.freemovieserver.domain.model.Role;
import org.xjh.freemovieserver.domain.model.User;

/**
 * @author xjh
 * @date 6/16/2022 7:44 PM
 */
@Mapper(componentModel = "spring")
public abstract class UserMapper implements Converter<User, UserDto> {

    /**
     * role to authority
     *
     * @param role role
     * @return authority
     */
    String roleToString(Role role) {
        return role.getAuthority();
    }

    /**
     * Convert User to UserDto
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null}) (required)
     * @return the converted object (never {@code null})
     */
    public abstract UserDto convert(User source);
}
