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

public class WebSecurityConfig  {
	@Autowired
    private UserDetailServiceImpl userDetailsService;
	// using lambdas 
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
      http
      .authorizeHttpRequests( authorize -> authorize
        	.requestMatchers(antMatcher("/carmuseum**")).permitAll() // Enables css when logged out
        	 .requestMatchers(toH2Console()).permitAll()
        	.anyRequest().authenticated()
      )
      .csrf(csrf -> csrf
              .ignoringRequestMatchers(toH2Console())
         )
         .headers(headers -> headers
             .frameOptions(frameoptions -> frameoptions
                      .disable())
         )
      .formLogin(formlogin -> formlogin
          .defaultSuccessUrl("/carmuseum", true)
          .permitAll()
      )
      .logout(logout -> logout
          .permitAll()
      );
      return http.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService (userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}