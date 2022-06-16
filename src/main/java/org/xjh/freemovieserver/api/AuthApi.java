package org.xjh.freemovieserver.api;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xjh.freemovieserver.domain.dto.ResultDto;
import org.xjh.freemovieserver.domain.model.User;

import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.stream.Collectors;

/**
 * @author xjh
 * @date 6/16/2022 10:01 AM
 */
@RestController
@RequestMapping("/auth")
public class AuthApi {

    final JwtEncoder encoder;

    public AuthApi(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    /**
     * 获取当前用户的jwt token
     *
     * @param authentication 账号密码的认证信息
     * @param response       响应
     * @return 用户操作的令牌
     */
    @PostMapping("/token")
    public ResultDto<String> getToken(Authentication authentication, HttpServletResponse response) {
        // 获取用户信息
        User user = (User) authentication.getPrincipal();
        // 获取用户的权限
        String scope = authentication
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        // 配置jwt的参数
        Instant now = Instant.now();
        long expiry = 36000L;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .claim("userId", user.getId())
                .build();
        // TODO 可以加入缓存，提高性能

        // 生成jwt token
        String tokenValue = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        // 设置响应头
        response.setHeader("Authorization", "Bearer " + tokenValue);

        return new ResultDto<>("success", "获取令牌成功", tokenValue);
    }
}
