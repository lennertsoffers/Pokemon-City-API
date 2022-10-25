package com.lennertsoffers.pokemon_city_api.config;

import com.lennertsoffers.pokemon_city_api.model.Role;
import com.lennertsoffers.pokemon_city_api.security.AuthDsl;
import com.lennertsoffers.pokemon_city_api.security.PermittedRoutes;
import com.lennertsoffers.pokemon_city_api.security.RoleType;
import com.lennertsoffers.pokemon_city_api.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * Security Configuration Class
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final RoleService roleService;

    /**
     * Bean that creates a record in the database for each role in the RoleType enum<br/>
     * To create a new role, add a new Literal to the enum<br/>
     *
     * @see RoleType
     */
    @Bean
    public void saveRoles() {
        Arrays.stream(RoleType.values()).forEach(roleType -> {
            if (!roleService.rolePersisted(roleType)) {
                roleService.saveRole(new Role(null, roleType.fullName()));
            }
        });
    }

    /**
     * Configures the passwordEncoder to for encryption in the application<br/>
     *
     * @return A new instance of BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Returns the AuthenticationManager bean in the application so that it is globally accessible
     * @param authenticationConfiguration the AuthenticationConfiguration of the application
     * @return The AuthenticationManager bean in the application
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Configures and returns the SecurityFilterChain for request coming in the application
     * @param http the HttpSecurity bean in the application
     * @return the configured SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeHttpRequests().antMatchers(PermittedRoutes.toArray()).permitAll();
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.apply(AuthDsl.authDsl());

        return http.build();
    }

    /**
     * Provides the WebMvcConfigurer instance to the application
     * @return A new instance of the WebConfig class
     * @see WebConfig
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebConfig();
    }
}
