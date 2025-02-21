package cz.cvut.fel.nss.SaunaStudio.security;

import cz.cvut.fel.nss.SaunaStudio.model.Admin;
import cz.cvut.fel.nss.SaunaStudio.model.Employee;
import cz.cvut.fel.nss.SaunaStudio.security.model.UserDetails;
import cz.cvut.fel.nss.SaunaStudio.model.User;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Užitečné metody pro práci s autentizačními informacemi uživatele.
 * <p>
 * Třída poskytuje metody pro získání aktuálně přihlášeného uživatele a jeho podrobnosti,
 * a také pro kontrolu anonymní autentizace.
 * </p>
 */
public class SecurityUtils {

    /**
     * Získá aktuálně přihlášeného uživatele.
     * <p>
     * Pokud je aktuálně přihlášený uživatel typu {@link UserDetails}, vrátí buď {@link Employee},
     * nebo {@link Admin} podle toho, kdo je přihlášen. Pokud není nikdo přihlášen, vrátí {@code null}.
     * </p>
     *
     * @return Aktuálně přihlášený uživatel nebo {@code null}, pokud není nikdo přihlášen
     */
    public static User getCurrentUser() {
        final UserDetails ud = getCurrentUserDetails();
        if (ud != null)
            return ud.getEmployee() == null ? ud.getAdmin() : ud.getEmployee();
        return null;
    }

    /**
     * Získá detaily aktuálně přihlášeného uživatele.
     * <p>
     * Pokud je aktuálně přihlášený uživatel typu {@link UserDetails}, vrátí jeho podrobnosti.
     * Pokud není nikdo přihlášen nebo pokud autentizace není typu {@link UserDetails}, vrátí {@code null}.
     * </p>
     *
     * @return Detaily aktuálně přihlášeného uživatele nebo {@code null}, pokud není nikdo přihlášen
     */
    public static UserDetails getCurrentUserDetails() {
        final SecurityContext context = SecurityContextHolder.getContext();
        if (context.getAuthentication() != null && context.getAuthentication().getPrincipal() instanceof UserDetails) {
            return (UserDetails) context.getAuthentication().getPrincipal();
        } else {
            return null;
        }
    }

    /**
     * Kontroluje, zda aktuální autentizační token představuje anonymního uživatele.
     * <p>
     * Pokud není nikdo přihlášen, vrátí {@code true}. Pokud je někdo přihlášen, vrátí {@code false}.
     * </p>
     *
     * @return {@code true}, pokud je aktuální autentizace anonymní, jinak {@code false}
     */
    public static boolean isAuthenticatedAnonymously() {
        return getCurrentUserDetails() == null;
    }
}
