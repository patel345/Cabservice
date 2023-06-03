package com.cabservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider
                = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return  provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
httpSecurity
                .authorizeHttpRequests().requestMatchers("/car-request/**").hasAuthority("ADMIN").and()
                .authorizeHttpRequests().requestMatchers("/assign-car/**").hasAuthority("ADMIN").and()
                .authorizeHttpRequests().requestMatchers("/delete-driver/**").hasAuthority("ADMIN").and()
                .authorizeHttpRequests().requestMatchers("/delete-car/**").hasAuthority("ADMIN").and()
                .authorizeHttpRequests().requestMatchers("/book-car/**").hasAuthority("USER").and()
                .authorizeHttpRequests().requestMatchers("/cancel-car/**").hasAuthority("USER").and()
                .authorizeHttpRequests().requestMatchers("/swagger-ui.html").permitAll().and()
                .authorizeHttpRequests(
                        auth -> auth.anyRequest().authenticated());
        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
        httpSecurity.headers().defaultsDisabled().cacheControl();
        return httpSecurity.build();
    }
}
