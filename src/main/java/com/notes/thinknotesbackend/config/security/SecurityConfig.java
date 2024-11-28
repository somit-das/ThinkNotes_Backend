package com.notes.thinknotesbackend.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests

                .requestMatchers("/contact").permitAll()
                .requestMatchers("/public/**").permitAll()  // any request that has public route will not get authenticated like /public/signup  and /public/signin
                .requestMatchers("/private/**").denyAll()
                .requestMatchers("/admin").denyAll()
                .requestMatchers("/admin/**").denyAll()
//                .requestMatchers("/admin/**").hasRole("ADMIN")
//                Request Matcher is good for when construction or maintainance of apis and for deprecated endpoints
                .anyRequest().authenticated());
//        http.formLogin(Customizer.withDefaults());

//        http.csrf(csrf->csrf.disable());
        http.sessionManagement((session) -> {session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);});
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inmanager = new InMemoryUserDetailsManager();
        if(!inmanager.userExists("admin")){
            inmanager.createUser(
                    User.withUsername("admin")
                            .password("{noop}password1")
                            .roles("ADMIN")
                            .build()
            );
        }
        if(!inmanager.userExists("user")){
            inmanager.createUser(
                    User.withUsername("user")
                            .password("{noop}password2")
                            .roles("USER")
                            .build()
            );
        }
        return inmanager;
    }
}
