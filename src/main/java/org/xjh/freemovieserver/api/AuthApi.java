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
import org.xjh.freemovieserver.domain.dto.TokenDto;
import org.xjh.freemovieserver.domain.model.User;
import org.xjh.freemovieserver.repository.redis.UserRedisRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author xjh
 * @date 6/16/2022 10:01 AM
 */
@RestController
@RequestMapping("/auth")
public class AuthApi {

    final JwtEncoder encoder;

    final UserRedisRepository userRedisRepository;

    public AuthApi(JwtEncoder encoder, UserRedisRepository userRedisRepository) {
        this.encoder = encoder;
        this.userRedisRepository = userRedisRepository;
    }

    /**
     * 获取当前用户的jwt token
     *
     * @param authentication 账号密码的认证信息
     * @return 用户操作的令牌
     */
    @PostMapping("/token")
    public ResultDto<TokenDto> getToken(Authentication authentication) {
        // 过期时长
        long expirationTimeLength = 36000L;

        // 获取用户信息
        User user = (User) authentication.getPrincipal();
        // 获取用户的权限
        String scope = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));

        // 配置jwt的参数
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expirationTimeLength))
                .subject(authentication.getName())
                .claim("scope", scope)
                .claim("userId", user.getId())
                .build();

        // 可以加入Rides缓存，提高性能
        user.setExpiration(expirationTimeLength);
        Optional<User> userRedis = userRedisRepository.findById(user.getId());
        if (userRedis.isEmpty()) {
            userRedisRepository.save(user);
        } else {
            userRedisRepository.deleteById(user.getId());
            userRedisRepository.save(user);
        }

        // 生成jwt token
        String tokenValue = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();


        // 计算过期时间
        LocalDateTime expireTime = LocalDateTime.ofInstant(now.plusSeconds(expirationTimeLength), ZoneId.systemDefault());

        tokenValue = "Bearer " + tokenValue;

        return new ResultDto<>("success", "获取令牌成功", new TokenDto(tokenValue, expireTime));
    }
}
