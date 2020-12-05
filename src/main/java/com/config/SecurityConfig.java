package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers(HttpMethod.DELETE, "/books/**", "/readers/**", "/issues/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/books/**", "/readers/**", "/issues/**", "/issues/addReader", "/issues/addBook").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/books", "/readers", "/issues").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }
}
