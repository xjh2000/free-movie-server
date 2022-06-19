package org.xjh.movie.api;

import io.smallrye.mutiny.Uni;
import org.xjh.movie.domain.dto.UserDto;
import org.xjh.movie.service.UserDtoRedisService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author xjh
 * @date 6/19/2022 4:56 PM
 */

@Path("/redis")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RedisTestApi {

    @Inject
    UserDtoRedisService userDtoRedisService;

    @POST
    @Path("/set")

    public Uni<Void> set(UserDto user) {
        return userDtoRedisService.set(user).map(response -> null);
    }

    @GET
    @Path("/get/{id}")
    public Uni<UserDto> get(String id) {
        return userDtoRedisService.get(id);
    }
}
