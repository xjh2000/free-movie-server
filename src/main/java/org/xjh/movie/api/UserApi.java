package org.xjh.movie.api;

import org.xjh.movie.domain.dto.UserDto;
import org.xjh.movie.domain.model.User;
import org.xjh.movie.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.stream.Stream;

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
    public Stream<UserDto> getAll() {
        return userService.getAll();
    }

    @GET
    @Path("findByUsername/{username}")
    public UserDto findByUsername(String username) {
        return userService.findByUsername(username);
    }

    @GET
    @Path("findById/{id}")
    public UserDto findById(String id) {
        return userService.findById(id);

    }


    @POST
    @Path("/register")
    public UserDto register(UserDto userDto) {
        return userService.register(userDto);
    }

}
