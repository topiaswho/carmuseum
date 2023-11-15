package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.demo.web.UserDetailServiceImpl;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    
    // Autowired UserDetailsService for user authentication
    @Autowired
    private UserDetailServiceImpl userDetailsService;
    
    // Bean for configuring the security filter chain
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(antMatcher("/carmuseum**")).permitAll() // Allows access to "/carmuseum" and its subpaths
                .requestMatchers(toH2Console()).permitAll() // Allows access to H2 console
                .anyRequest().authenticated() // Requires authentication for all other requests
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(toH2Console()) // Ignores CSRF protection for H2 console requests
            )
            .headers(headers -> headers
                .frameOptions(frameoptions -> frameoptions
                    .disable()) // Disables X-Frame-Options header for H2 console
            )
            .formLogin(formlogin -> formlogin
                .defaultSuccessUrl("/carmuseum", true) // Redirects to "/carmuseum" on successful login
                .permitAll() // Allows access to login page for all users
            )
            .logout(logout -> logout
                .permitAll() // Allows access to logout endpoint for all users
            );
        return http.build();
    }

    // Bean for defining the PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configures global authentication with the provided UserDetailsService and PasswordEncoder
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
