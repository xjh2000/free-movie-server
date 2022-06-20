package org.xjh.movie.api;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.xjh.movie.domain.dto.UserDto;
import org.xjh.movie.service.UserService;
import org.xjh.movie.throwable.model.BaseError;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

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
    @RolesAllowed("admin")
    public List<UserDto> getAll() {
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
    @Path("/destroy")
    @RolesAllowed("user")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "成功"),
            @APIResponse(responseCode = "400", description = "用户不存在"),
            @APIResponse(responseCode = "401", description = "没有认证"),
    })
    public void destroy(@Context SecurityContext securityContext) {
        String username = securityContext.getUserPrincipal().getName();
        userService.destroy(username);
    }

    @POST
    @Path("/register")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "成功"),
            @APIResponse(responseCode = "400", description = "用户已存在" ,content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseError.class))),
    })
    public UserDto register(UserDto userDto) {
        return userService.register(userDto);
    }

}
