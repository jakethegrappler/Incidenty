package cz.cvut.fel.MuzeumSys.rest.handler;

import cz.cvut.fel.nss.SaunaStudio.exception.NotFoundException;
import cz.cvut.fel.nss.SaunaStudio.exception.PersistenceException;
import cz.cvut.fel.nss.SaunaStudio.exception.UnauthorizedException;
import cz.cvut.fel.nss.SaunaStudio.security.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Exception handler pro REST kontroléry.
 * <p>
 * Obecný vzor by měl být takový, že pokud výjimka nemůže být zpracována na vhodnějším místě, bublá nahoru k REST
 * kontroléru, který původně obdržel požadavek. Tam je zachycena tímto zpracovatelem, zalogována a vrátí se uživateli
 * rozumná chybová zpráva.
 */
@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * Loguje výjimku.
     *
     * @param ex Výjimka, kterou je třeba zalogovat
     */
    private static void logException(RuntimeException ex) {
        LOG.error("Exception caught:", ex);
    }

    /**
     * Vytváří instanci {@link ErrorInfo} obsahující informace o chybě.
     *
     * @param request Požadavek, který vedl k chybě
     * @param e Chyba, která se stala
     * @return {@link ErrorInfo} obsahující zprávu o chybě a URI požadavku
     */
    private static ErrorInfo errorInfo(HttpServletRequest request, Throwable e) {
        return new ErrorInfo(e.getMessage(), request.getRequestURI());
    }

    /**
     * Zpracovává výjimky typu {@link PersistenceException}.
     *
     * @param request Požadavek, který vedl k chybě
     * @param e Výjimka, která se stala
     * @return {@link ResponseEntity} s informacemi o chybě a HTTP stavovým kódem 500 (INTERNAL_SERVER_ERROR)
     */
    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<ErrorInfo> persistenceException(HttpServletRequest request, PersistenceException e) {
        logException(e);
        return new ResponseEntity<>(errorInfo(request, e.getCause()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Zpracovává výjimky typu {@link NotFoundException}.
     *
     * @param request Požadavek, který vedl k chybě
     * @param e Výjimka, která se stala
     * @return {@link ResponseEntity} s informacemi o chybě a HTTP stavovým kódem 404 (NOT_FOUND)
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorInfo> resourceNotFound(HttpServletRequest request, NotFoundException e) {
        // Není nutné logovat NotFoundException, mohou být poměrně časté a nepředstavují problém s aplikací
        return new ResponseEntity<>(errorInfo(request, e), HttpStatus.NOT_FOUND);
    }

    /**
     * Zpracovává výjimky typu {@link UnsupportedAudioFileException} a {@link UnauthorizedException}.
     *
     * @param request Požadavek, který vedl k chybě
     * @param e Výjimka, která se stala
     * @return {@link ResponseEntity} s informacemi o chybě a HTTP stavovým kódem 409 (CONFLICT)
     */
    @ExceptionHandler(UnsupportedAudioFileException.class)
    public ResponseEntity<ErrorInfo> validation(HttpServletRequest request, UnauthorizedException e) {
        logException(e);
        return new ResponseEntity<>(errorInfo(request, e), HttpStatus.CONFLICT);
    }

    /**
     * Zpracovává výjimky typu {@link AccessDeniedException}.
     *
     * @param request Požadavek, který vedl k chybě
     * @param e Výjimka, která se stala
     * @return {@link ResponseEntity} s informacemi o chybě a HTTP stavovým kódem 403 (FORBIDDEN) pokud není uživatel anonymní,
     *         jinak je výjimka znovu vyhozena, aby ji zpracoval autentizační vstupní bod
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorInfo> accessDenied(HttpServletRequest request, AccessDeniedException e) {
        // Spring Boot hází Access Denied, když se pokoušíte přistupovat k zabezpečené metodě s anonymním autentizačním tokenem
        // Chceme nechat takovou výjimku ven, aby ji zpracoval autentizační vstupní bod (který vrací 401)
        if (SecurityUtils.isAuthenticatedAnonymously()) {
            throw e;
        }
        logException(e);
        return new ResponseEntity<>(errorInfo(request, e), HttpStatus.FORBIDDEN);
    }

}
