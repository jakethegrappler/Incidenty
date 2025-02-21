package cz.cvut.fel.nss.SaunaStudio.security.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Reprezentuje stav přihlášení uživatele.
 * <p>
 * Tato třída uchovává informace o aktuálním stavu přihlášení, včetně uživatelského jména,
 * chybových zpráv a stavu úspěšnosti operace přihlášení.
 * </p>
 */
@Setter
@Getter
public class LoginStatus {

    /**
     * Indikuje, zda je uživatel přihlášen.
     */
    private boolean loggedIn;

    /**
     * Uživatelovo jméno.
     */
    private String username;

    /**
     * Chybová zpráva v případě neúspěšného přihlášení.
     */
    private String errorMessage;

    /**
     * Indikuje, zda byla operace přihlášení úspěšná.
     */
    private boolean success;

    /**
     * Konstruktor pro {@link LoginStatus}.
     *
     * @param loggedIn    Indikuje, zda je uživatel přihlášen
     * @param success     Indikuje, zda byla operace přihlášení úspěšná
     * @param username    Uživatelovo jméno
     * @param errorMessage Chybová zpráva v případě neúspěšného přihlášení
     */
    public LoginStatus(boolean loggedIn, boolean success, String username, String errorMessage) {
        this.loggedIn = loggedIn;
        this.username = username;
        this.errorMessage = errorMessage;
        this.success = success;
    }
}
