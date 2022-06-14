package org.xjh.freemovieserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.xjh.freemovieserver.repository.UserRepository;

import static java.lang.String.format;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final UserRepository userInfoRepository;

    public WebSecurityConfig(UserRepository userRepository) {
        this.userInfoRepository = userRepository;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.userDetailsService(
                username ->
                        userInfoRepository
                                .findByUsername(username)
                                .orElseThrow(
                                        () -> new UsernameNotFoundException(format("用户名: %s, 查询不到", username))
                                )

        );

        http.authorizeRequests()
                .antMatchers("/user/**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic(Customizer.withDefaults());
        return http.build();

    }

    // Set password encoding schema
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
