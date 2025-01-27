package kumo.api.api.Application.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .requestMatchers(HttpMethod.POST, "/artist/create/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/artist/login/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/artist/allArtists/").permitAll()
            .requestMatchers(
                "/swagger-ui/**", 
                "/v3/api-docs/**",
                "/swagger-ui.html",
                "/webjars/**"
            ).permitAll()
            .requestMatchers("/art/**").authenticated()
            .requestMatchers("/admin/**").hasRole("Admin")
            .anyRequest().authenticated()
            .and()
            .formLogin()  
                .loginProcessingUrl("/login")  
                .usernameParameter("email")  
                .passwordParameter("password") 
            .and()
            .httpBasic();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }
}
