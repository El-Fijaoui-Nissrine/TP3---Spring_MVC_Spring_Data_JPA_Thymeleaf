package com.example.hopital.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity


public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
return new InMemoryUserDetailsManager(User.withUsername("user1").password(passwordEncoder.encode("11111")).roles("USER").build(),
        User.withUsername("nissrine").password(passwordEncoder.encode("1234")).roles("USER").build(),
        User.withUsername("admin").password(passwordEncoder.encode("1212")).roles("USER","ADMIN").build()
);

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.formLogin(form->form.loginPage("/login").permitAll())
                .authorizeHttpRequests(ar->ar.requestMatchers("/admin/**").hasRole("ADMIN"))
                .authorizeHttpRequests(ar->ar.requestMatchers("/user/**").hasRole("USER"))
                .authorizeHttpRequests(ar->ar.requestMatchers("/webjars/**").permitAll())

                .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/notAuthorized") // Page d'accès refusé personnalisée
                );
        return  httpSecurity.build();


    }


}