package org.xjh.freemovieserver.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    @NotNull
    @Size(min = 6, max = 20, message = "用户名长度必须在6-20之间")
    private String username;
    @NotNull
    @Size(min = 6, max = 20, message = "密码长度必须在6-20之间")
    private String password;

}