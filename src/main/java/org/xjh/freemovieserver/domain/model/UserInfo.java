package org.xjh.freemovieserver.domain.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;

@Document(collection = "user_info")
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class UserInfo {

    @NonNull
    @Size(min = 6, message = "用户名长度不能小于6")
    private String username;
    @NonNull
    @Size(min = 6, message = "密码长度不能小于6")
    private String password;

}