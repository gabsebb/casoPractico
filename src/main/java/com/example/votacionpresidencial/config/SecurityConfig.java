package com.example.votacionpresidencial.config;

import com.example.votacionpresidencial.repositories.UsuarioRepository;
import com.example.votacionpresidencial.services.CustomUserDetailsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/login",
                                "/registro",
                                "/error",
                                "/static/**",
                                "/css/**",
                                "/js/**",
                                "/assets/**",
                                "/img/**",
                                "/acceso-denegado"
                        ).permitAll()
                        .requestMatchers("/dashboard",
                                "/admin",
                                "/personas/**",
                                "/eliminarUsu/{id}",
                                "/editarUsu/{id}",
                                "/partidos/**",
                                "/candidatos/**",
                                "/reportes/**").hasRole("ADMIN")
                        .requestMatchers("/votacion/**").hasRole("VOTANTE")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/acceso-denegado")
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(customAuthenticationSuccessHandler())
                        .failureUrl("/login?error=true")
                        .permitAll()
                ).rememberMe(remember -> remember
                        .key("177013")
                        .tokenValiditySeconds(86400)
                        .rememberMeParameter("remember-me")
                        .rememberMeCookieName("remember-me")
                        .userDetailsService(userDetailsService)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                        .permitAll()
                );

        http.csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        );

        return http.build();
    }
    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            String targetUrl = "/"; // Redirección por defecto

            if (authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                targetUrl = "/dashboard";
            } else if (authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_VOTANTE"))) {
                targetUrl = "/votacion";
            }

            HttpSession session = request.getSession(false);
            if (session != null) {
                String redirectUrl = (String) session.getAttribute("url_prior_login");
                if (redirectUrl != null && !redirectUrl.isEmpty()) {
                    targetUrl = redirectUrl;
                    session.removeAttribute("url_prior_login");
                }
            }

            response.sendRedirect(targetUrl);
        };
    }
}
