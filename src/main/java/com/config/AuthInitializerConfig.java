package com.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;

@Configuration
@ConditionalOnClass(AuthenticationManagerBuilder.class) // включает механизм автоконфигураций
public class AuthInitializerConfig {

    @Profile("dev")
    @Bean
    public UserDetailsManagerConfigurer.UserDetailsBuilder configureForDev(AuthenticationManagerBuilder auth) throws Exception {
        return auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN").and()
                .withUser("user").password("{noop}password").roles("USER");
    }

    @Profile("prod")
    @Bean
    public UserDetailsManagerConfigurer.UserDetailsBuilder configureForProd(AuthenticationManagerBuilder auth) throws Exception {
        return auth.inMemoryAuthentication()
                .withUser("ADMINISTRATOR").password("{noop}pass123").roles("USER", "ADMIN").and()
                .withUser("myLogin").password("{noop}parol111").roles("USER").and()
                .withUser("jmXr").password("{noop}TABAV").roles("USER");
    }
}
