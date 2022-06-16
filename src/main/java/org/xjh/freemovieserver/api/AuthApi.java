package org.xjh.freemovieserver.api;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/token")
    public String token(Authentication authentication, HttpServletResponse response) {
        User user = (User) authentication.getPrincipal();
        Instant now = Instant.now();
        long expiry = 36000L;

        String scope = authentication
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .claim("userId", user.getId())
                .build();

        String tokenValue = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        response.setHeader("Authorization", "Bearer " + tokenValue);
        return tokenValue;

    }
}
