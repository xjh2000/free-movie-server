package org.xjh.movie.domain.mapper;

/**
 * @author xjh
 * @date 6/19/2022 2:47 PM
 */

import org.mapstruct.Mapper;
import org.xjh.movie.domain.dto.UserDto;
import org.xjh.movie.domain.model.User;

@Mapper(componentModel = "cdi")
public interface UserMapper {

    UserDto toDto(User user);


}
