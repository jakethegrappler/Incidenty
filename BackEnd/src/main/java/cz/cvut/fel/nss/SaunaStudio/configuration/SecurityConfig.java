package cz.cvut.fel.nss.SaunaStudio.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.nss.SaunaStudio.security.AuthenticationSuccess;
import cz.cvut.fel.nss.SaunaStudio.security.AuthenticationFailure;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Konfigurační třída pro nastavení bezpečnostní konfigurace aplikace.
 * Tato třída definuje pravidla pro zabezpečení aplikace, včetně konfigurace
 * přístupu, CORS, CSRF, a přihlašování/odhlašování uživatelů.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final ObjectMapper objectMapper;

    /**
     * Konstruktor pro inicializaci {@link SecurityConfig} s {@link ObjectMapper}.
     *
     * @param objectMapper {@link ObjectMapper} používaný pro zpracování JSON během autentizace.
     */
    public SecurityConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Definuje bean pro {@link SecurityFilterChain}, který konfiguruje pravidla zabezpečení aplikace.
     *
     * <p>Nastavuje pravidla pro autorizaci požadavků, správu výjimek, CORS, CSRF, hlavičky a přihlašování/odhlašování.</p>
     *
     * @param http {@link HttpSecurity} pro konfiguraci bezpečnostních nastavení.
     * @return instance {@link SecurityFilterChain} s definovanými bezpečnostními pravidly.
     * @throws Exception Pokud dojde k chybě při konfiguraci.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        final AuthenticationSuccess authSuccess = authenticationSuccess();
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Úprava dle potřeby pro vaše pravidla autorizace
                )
                .exceptionHandling(ehc -> ehc
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .csrf(AbstractHttpConfigurer::disable) // Zakázání ochrany CSRF, konfigurovat dle potřeby
                .cors(conf -> conf
                        .configurationSource(corsConfigurationSource())
                )
                .headers(customizer -> customizer
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin) // Prevence clickjacking
                )
                .formLogin(fl -> fl
                        .successHandler(authSuccess)
                        .failureHandler(authenticationFailureHandler())
                )
                .logout(lgt -> lgt
                        .logoutSuccessHandler(authSuccess)
                );
        return http.build();
    }

    /**
     * Vytváří instanci {@link AuthenticationFailure} pro zpracování neúspěšného přihlášení.
     *
     * @return instance {@link AuthenticationFailure}.
     */
    private AuthenticationFailure authenticationFailureHandler() {
        return new AuthenticationFailure(objectMapper);
    }

    /**
     * Vytváří instanci {@link AuthenticationSuccess} pro zpracování úspěšného přihlášení.
     *
     * @return instance {@link AuthenticationSuccess}.
     */
    private AuthenticationSuccess authenticationSuccess() {
        return new AuthenticationSuccess(objectMapper);
    }

    /**
     * Definuje bean pro {@link CorsConfigurationSource} pro nastavení CORS.
     *
     * <p>Nastavuje povolené původy, metody, hlavičky a další CORS konfigurace.</p>
     *
     * @return instance {@link CorsConfigurationSource} s definovanými CORS pravidly.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));  // Specifikujte povolené původy
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));  // Specifikujte povolené metody
        configuration.setAllowedHeaders(List.of("*"));  // Povolit všechny hlavičky
        configuration.addExposedHeader(HttpHeaders.LOCATION);
        configuration.setAllowCredentials(true);  // Povolit přihlašovací údaje

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
