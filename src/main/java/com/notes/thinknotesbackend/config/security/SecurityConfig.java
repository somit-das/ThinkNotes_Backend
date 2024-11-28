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
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

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
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager jmanager = new JdbcUserDetailsManager(dataSource);
        if(!jmanager.userExists("admin")){
            jmanager.createUser(
                    User.withUsername("admin")
                            .password("{noop}password1")
                            .roles("ADMIN")
                            .build()
            );
        }
        if(!jmanager.userExists("user")){
            jmanager.createUser(
                    User.withUsername("user")
                            .password("{noop}password2")
                            .roles("USER")
                            .build()
            );
        }
        return jmanager;
    }

/* For ABOVE JdbcUserDetailsManager  Default User Based on User entity based on JdbcDao has to be create table in provided database

create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);


 */
/*
 Here in  current setup that we have with JdbcUserDetails Manager works over here without specifying  those additional fields ( the account expiration, account locking, and credentials expiration)  as well  and the reason for this is because the user class, which JDBC User Details Manager uses to create the
user, provides default values which is true for the account expiration, account locking, and credentials expiration. So
And these defaults ensure that the users are always considered as non expired, non locked and with non expired credentials unless explicitly specified otherwise.

This design allows spring security to function correctly with minimal schema, leveraging default assumptions for properties not explicitly stored in the database.
so we are not storing any of these properties in the database like you just saw in the user class

That is the reason we have to create custom User Entity to store  and UserDetails Implemented Class for Verification
*/

}
