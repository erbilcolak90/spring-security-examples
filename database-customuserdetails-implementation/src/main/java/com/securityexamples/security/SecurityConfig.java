package com.securityexamples.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.httpBasic();


        /*
        There is no difference between hasAuthority and hasRole. Its about role or authority name prefix
        - createUser method has no authority. Everyone can access this method.
        - Who has authority READ can access api/** patterns with HttpMethod.GET
        - requestMatchers.access using for user who already authenticated
         */

        http.authorizeHttpRequests()
                .requestMatchers("/createUser").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/**").hasAuthority("READ")
                .requestMatchers("/demo").hasAuthority("WRITE")
                .requestMatchers("/api/test/{testString}").access(new WebExpressionAuthorizationManager("isAuthenticated()"));

        http.csrf().ignoringRequestMatchers("/createUser");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
