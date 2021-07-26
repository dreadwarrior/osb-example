package de.dreadlabs.osbexample;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityWebFilterChain(
            ServerHttpSecurity http
    ) {
        http.authorizeExchange()
                .pathMatchers("/v2/**")
                    .authenticated()
                .pathMatchers("/**").permitAll()
                .and().httpBasic()
                .and().formLogin().disable();

        return http.build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService(
            ApplicationConfigProperties configProperties
    ) {
        UserDetails user = User
                .withUsername(configProperties.userName())
                .password(passwordEncoder().encode(configProperties.password()))
                .roles("ADMIN")
                .build();

        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
