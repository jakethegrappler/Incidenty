package cz.cvut.fel.nss.SaunaStudio.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.nss.SaunaStudio.security.model.LoginStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

/**
 * Zpracovává selhání autentizace a vrací vlastní JSON odpověď.
 * <p>
 * Tato implementace se liší od výchozí tím, že vrací vlastní JSON odpověď namísto standardního chybového formátu.
 * </p>
 */
public class AuthenticationFailure implements AuthenticationFailureHandler {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationFailure.class);

    private final ObjectMapper mapper;

    /**
     * Konstruktor pro {@link AuthenticationFailure}.
     *
     * @param mapper {@link ObjectMapper} pro serializaci objektu {@link LoginStatus} do JSON
     */
    public AuthenticationFailure(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Zpracovává selhání autentizace.
     * <p>
     * Při selhání autentizace je zaznamenána chybová zpráva a vrácena JSON odpověď obsahující stav přihlášení a zprávu o chybě.
     * </p>
     *
     * @param httpServletRequest  {@link HttpServletRequest} obsahující informace o požadavku
     * @param httpServletResponse {@link HttpServletResponse} pro vrácení odpovědi
     * @param e                  {@link AuthenticationException} obsahující podrobnosti o selhání autentizace
     * @throws IOException pokud dojde k chybě při zápisu do odpovědi
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException {
        LOG.debug("Přihlášení selhalo pro uživatele {}.", httpServletRequest.getParameter("username"));
        final LoginStatus status = new LoginStatus(false, false, null, e.getMessage());
        mapper.writeValue(httpServletResponse.getOutputStream(), status);
    }
}
