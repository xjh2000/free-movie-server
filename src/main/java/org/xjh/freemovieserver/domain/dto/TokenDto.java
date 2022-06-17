package org.xjh.freemovieserver.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author xjh
 * @date 6/16/2022 4:24 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    private String accessToken;
    private LocalDateTime expireTime;
}
