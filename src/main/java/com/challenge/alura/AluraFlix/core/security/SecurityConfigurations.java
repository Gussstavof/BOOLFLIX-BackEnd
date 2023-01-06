package com.challenge.alura.AluraFlix.core.security;

import com.challenge.alura.AluraFlix.core.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations{
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
         return httpSecurity.csrf().disable()
                 .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                 .and().authorizeHttpRequests()
                 .antMatchers(HttpMethod.POST, "/videos/").hasRole("ADM")
                 .antMatchers(HttpMethod.DELETE, "/videos/*").hasRole("ADM")
                 .antMatchers(HttpMethod.PUT, "/videos/*").hasRole("ADM")
                 .antMatchers(HttpMethod.POST, "/categories").hasRole("ADM")
                 .antMatchers(HttpMethod.PUT, "/categories/*").hasRole("ADM")
                 .antMatchers(HttpMethod.POST,"/authentication").permitAll()
                 .anyRequest().authenticated()
                 .and().build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
