package kh.edu.istad.bankingapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Bean
    DaoAuthenticationProvider  daoAuthenticationProvider() {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return daoAuthenticationProvider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers(HttpMethod.POST ,"/api/v1/customers/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE ,"/api/v1/customers/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT ,"/api/v1/customers/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH ,"/api/v1/customers/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET ,"/api/v1/customers/**").hasAnyRole("ADMIN", "STAFF")
                        .requestMatchers(HttpMethod.POST ,"/api/v1/accounts/**").hasRole("USER")
                        .anyRequest()
                        .authenticated());

//        disable form login
        http.formLogin(FormLoginConfigurer::disable);

        http.httpBasic(withDefaults());

//        disable CSRF
        http.csrf(AbstractHttpConfigurer::disable);

//        change to Stateless
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

}
