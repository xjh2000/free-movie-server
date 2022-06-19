package org.xjh.movie.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.redis.client.reactive.ReactiveRedisClient;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.redis.client.Response;
import org.xjh.movie.domain.dto.UserDto;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Objects;

/**
 * @author xjh
 * @date 6/19/2022 4:23 PM
 */
@Singleton
public class UserDtoRedisService {
    @Inject
    ReactiveRedisClient reactiveRedisClient;
    @Inject
    ObjectMapper objectMapper;
    final Integer expire = 60 * 60 * 24; // 24 hours

    public Uni<Response> set(UserDto user) {
        try {
            return reactiveRedisClient
                    .set(List.of(user.id, objectMapper.writeValueAsString(user)))
                    .onItem().call(() ->
                            reactiveRedisClient.expire(user.id, expire.toString())
                    );
        } catch (JsonProcessingException e) {
            return Uni.createFrom().failure(e);
        }
    }

    public Uni<UserDto> get(String id) {
        return reactiveRedisClient.get(id)
                .map((response -> {
                    if (Objects.isNull(response)) {
                        return null;
                    }
                    try {
                        return objectMapper.readValue(response.toString(), UserDto.class);
                    } catch (JsonProcessingException e) {
                        return null;
                    }
                }));
    }
}
