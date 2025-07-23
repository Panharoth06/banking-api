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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
//    @Bean
//    InMemoryUserDetailsManager  userDetailsManager() {
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("admin123"))
//                .roles("ADMIN", "CUSTOMER")
//                .build();
//
//        UserDetails editor = User.builder()
//                .username("editor")
//                .password(passwordEncoder.encode("editor123"))
//                .roles("EDITOR", "CUSTOMER")
//                .build();
//
//        UserDetails customer = User.builder()
//                .roles("CUSTOMER")
//                .username("customer")
//                .password(passwordEncoder.encode("customer123"))
//                .build();
//
//        manager.createUser(admin);
//        manager.createUser(editor);
//        manager.createUser(customer);
//
//        return manager;
//    }

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
                        .requestMatchers(HttpMethod.GET ,"/api/v1/customers/**").hasAnyRole("ADMIN", "EDITOR")
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
