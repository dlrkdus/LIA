package com.project.LIA.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import role.Role;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SpringSecurityConfig extends WebSecurityConfiguration {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //Url별 권한 관리를 설정하는 옵션의 시작점
                .authorizeHttpRequests((requests) -> requests
                        //모두 열람 가능한 url
                        .requestMatchers("/","/home").permitAll()
                        //User 권한 가진 사람만 열람 가능
                        .requestMatchers("/api/v1/**").hasAnyRole(Role.USER.name())
                        //나머지 모든 요청은 모두 인증된 사용자들에게만 허용
                        .anyRequest().authenticated()
                )
                //logout 성공시 "/" 주소로 이동
                .logout((logout) -> logout.logoutSuccessUrl("/"))
                .oauth2Login(oauth2 -> oauth2
                        // Oauth 로그인 성공 이후 사용자 정보 가져올 때 설정 담당
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        )
                );
        return http.build();
    }

}
