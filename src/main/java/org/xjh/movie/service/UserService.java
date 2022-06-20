package org.xjh.movie.service;

import org.xjh.movie.domain.dto.UserDto;
import org.xjh.movie.domain.mapper.UserMapper;
import org.xjh.movie.domain.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.Set;

/**
 * @author xjh
 * @date 6/19/2022 2:17 PM
 */
@ApplicationScoped
public class UserService {

    @Inject
    UserMapper userMapper;

    public UserDto findByUsername(String username) {
        User dbUser = User.find("username", username).firstResult();
        if (dbUser == null) {
            throw new WebApplicationException("User not found", 404);
        }
        return userMapper.toDto(dbUser);
    }

    @Transactional
    public UserDto register(UserDto userDto) {
        // TODO Internal Server Error 400 改为自定义异常返回
        userDto.roles = Set.of("user");
        User user = userMapper.toEntity(userDto);
        if (User.find("username", user.username).firstResult() != null) {
            throw new WebApplicationException("User already exists", 400);
        }
        User.register(user.username, user.password, user.roles);
        return userMapper.toDto(user);
    }


    public List<UserDto> getAll() {
        List<User> users = User.findAll().list();
        return userMapper.toDto(users);
    }

    public UserDto findById(String id) {
        User dbUser = User.findById(Long.valueOf(id));
        if (dbUser == null) {
            throw new WebApplicationException("User not found", 404);
        }
        return userMapper.toDto(dbUser);

    }

    @Transactional
    public void destroy(String username) {
        // TODO Internal Server Error 404 改为自定义异常返回
        User user = User.find("username", username).firstResult();
        if (user == null) {
            throw new WebApplicationException("User not found", 404);
        }
        user.delete();
    }
}
