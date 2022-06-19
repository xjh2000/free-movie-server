package org.xjh.movie.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.xjh.movie.domain.dto.UserDto;
import org.xjh.movie.domain.mapper.UserMapper;
import org.xjh.movie.domain.model.User;
import org.xjh.movie.repo.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 * @author xjh
 * @date 6/19/2022 2:17 PM
 */
@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;
    @Inject
    UserMapper userMapper;

    public Uni<UserDto> findByUsername(String username) {
        return userRepository
                .find("username", username)
                .firstResult()
                .map(userMapper::toDto);
    }

    public Uni<UserDto> register(User user) {
        // TODO Internal Server Error 500 改为自定义异常返回
        return userRepository.findByUsername(user.username)
                .onItem().ifNotNull().failWith(() -> new WebApplicationException("用户已存在"))
                .onItem().ifNull().switchTo(() -> userRepository.persist(user)).map(userMapper::toDto);
    }


    public Multi<UserDto> getAll() {
        return userRepository.findAll().stream().map(userMapper::toDto);
    }

    public Uni<UserDto> findById(String id) {
        return userRepository.findById(new ObjectId(id)).map(userMapper::toDto);
    }
}
