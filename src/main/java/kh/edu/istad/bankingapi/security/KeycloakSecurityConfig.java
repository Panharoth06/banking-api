package kh.edu.istad.bankingapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class KeycloakSecurityConfig {

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        Converter<Jwt, Collection<GrantedAuthority>> jwtConverter = jwt -> {
            Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
            Collection<String> roles = realmAccess.get("roles");
            return roles.stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toList());
        };

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtConverter);
        return jwtAuthenticationConverter;
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
                        .requestMatchers("/media/**").permitAll()
                        .requestMatchers("/api/v1/medias/**").permitAll()
                        .anyRequest()
                        .authenticated());

//        disable form login
        http.formLogin(FormLoginConfigurer::disable);

        http.oauth2ResourceServer(oauth2ResourceServerConfigurer ->
                oauth2ResourceServerConfigurer.jwt(withDefaults()));

//        disable CSRF
        http.csrf(AbstractHttpConfigurer::disable);

//        change to Stateless
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

}
