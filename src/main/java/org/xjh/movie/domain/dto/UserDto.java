package org.xjh.movie.domain.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Set;

/**
 * @author xjh
 * @date 6/19/2022 1:32 PM
 */
public class UserDto {
    public Long id;
    public String username;

    public String password;
    @Schema(nullable = true)
    public String mail;

    public Set<String> roles;

}
