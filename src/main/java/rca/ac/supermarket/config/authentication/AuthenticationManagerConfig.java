package rca.ac.supermarket.config.authentication;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import rca.ac.supermarket.authentication.JwtAuthenticationProvider;
import rca.ac.supermarket.services.CustomUserDetailsService;

@Configuration
public class AuthenticationManagerConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationManagerConfig(CustomUserDetailsService customUserDetailsService,
                                       JwtAuthenticationProvider jwtAuthenticationProvider,
                                       PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
        auth.authenticationProvider(jwtAuthenticationProvider);
        return auth.build();
    }
}
