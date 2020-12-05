package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/issues").hasRole("ADMIN")
                .antMatchers("/", "/v2/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security", "/swagger-ui.html", "/webjars/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE,"/books/**", "/readers/**", "/issues/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/books/**", "/readers/**", "/issues/**", "/issues/addReader", "/issues/addBook").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/books", "/readers", "/issues").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN").and()
                .withUser("user").password("{noop}password").roles("USER");
    }
}
