package org.xjh.movie.domain.mapper;



import org.mapstruct.Mapper;
import org.xjh.movie.domain.dto.UserDto;
import org.xjh.movie.domain.model.User;

/**
 * @author xjh
 * @date 6/19/2022 2:47 PM
 */
@Mapper(componentModel = "cdi")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
