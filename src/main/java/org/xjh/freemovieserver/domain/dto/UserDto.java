package org.xjh.freemovieserver.domain.dto;

import lombok.Data;

import java.util.Set;

/**
 * @author xjh
 * @date 6/16/2022 5:01 PM
 */
@Data
public class UserDto {
    private String id;
    private String username;
    private Set<String> authorities;
}
