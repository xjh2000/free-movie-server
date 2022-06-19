package org.xjh.movie.api;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.xjh.movie.domain.dto.UserDto;
import org.xjh.movie.domain.model.User;
import org.xjh.movie.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author xjh
 * @date 6/18/2022 8:36 PM
 */
@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserApi {


    @Inject
    UserService userService;

    @GET
    @Path("/getAll")
    public Multi<UserDto> getAll() {
        return userService.getAll();
    }

    @GET
    @Path("findByUsername/{username}")
    public Uni<UserDto> findByUsername(String username) {
        return userService.findByUsername(username);

    }

    @GET
    @Path("findById/{id}")
    public Uni<UserDto> findById(String id) {
        return userService.findById(id);

    }


    @POST
    @Path("/register")
    public Uni<UserDto> register(User user) {
        return userService.register(user);
    }

}
