package cz.cvut.fel.nss.SaunaStudio.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.nss.SaunaStudio.security.model.UserDetails;
import cz.cvut.fel.nss.SaunaStudio.security.model.LoginStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

/**
 * Zpracovává úspěšnou autentizaci a odhlášení a vrací vlastní JSON odpověď.
 * <p>
 * Tato implementace se liší od výchozí tím, že vrací vlastní JSON odpověď namísto standardního formátu.
 * </p>
 */
public class AuthenticationSuccess implements AuthenticationSuccessHandler, LogoutSuccessHandler {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationSuccess.class);

    private final ObjectMapper mapper;

    /**
     * Konstruktor pro {@link AuthenticationSuccess}.
     *
     * @param mapper {@link ObjectMapper} pro serializaci objektu {@link LoginStatus} do JSON
     */
    public AuthenticationSuccess(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Zpracovává úspěšnou autentizaci.
     * <p>
     * Při úspěšném přihlášení je zaznamenáno jméno uživatele a vrácena JSON odpověď obsahující stav přihlášení.
     * </p>
     *
     * @param httpServletRequest  {@link HttpServletRequest} obsahující informace o požadavku
     * @param httpServletResponse {@link HttpServletResponse} pro vrácení odpovědi
     * @param authentication      {@link Authentication} obsahující informace o úspěšném přihlášení
     * @throws IOException pokud dojde k chybě při zápisu do odpovědi
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        final String username = getUsername(authentication);
        if (LOG.isTraceEnabled()) {
            LOG.trace("Úspěšně přihlášen uživatel {}", username);
        }
        final LoginStatus loginStatus = new LoginStatus(true, authentication.isAuthenticated(), username, null);
        mapper.writeValue(httpServletResponse.getOutputStream(), loginStatus);
    }

    /**
     * Vrací uživatelské jméno ze {@link Authentication}.
     *
     * @param authentication {@link Authentication} obsahující informace o uživatelském jménu
     * @return uživatelské jméno, nebo prázdný řetězec, pokud je {@link Authentication} null
     */
    private String getUsername(Authentication authentication) {
        if (authentication == null) {
            return "";
        }
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    /**
     * Zpracovává úspěšné odhlášení.
     * <p>
     * Při úspěšném odhlášení je zaznamenáno jméno uživatele a vrácena JSON odpověď obsahující stav odhlášení.
     * </p>
     *
     * @param httpServletRequest  {@link HttpServletRequest} obsahující informace o požadavku
     * @param httpServletResponse {@link HttpServletResponse} pro vrácení odpovědi
     * @param authentication      {@link Authentication} obsahující informace o odhlášení
     * @throws IOException pokud dojde k chybě při zápisu do odpovědi
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Authentication authentication) throws IOException {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Úspěšně odhlášen uživatel {}", getUsername(authentication));
        }
        final LoginStatus loginStatus = new LoginStatus(false, true, null, null);
        mapper.writeValue(httpServletResponse.getOutputStream(), loginStatus);
    }
}
