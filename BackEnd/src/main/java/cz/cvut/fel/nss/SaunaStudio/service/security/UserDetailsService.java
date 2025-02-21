package cz.cvut.fel.nss.SaunaStudio.service.security;

import cz.cvut.fel.nss.SaunaStudio.dao.AdminDao;
import cz.cvut.fel.nss.SaunaStudio.dao.CustomerDao;
import cz.cvut.fel.nss.SaunaStudio.model.Admin;
import cz.cvut.fel.nss.SaunaStudio.model.Employee;
import cz.cvut.fel.nss.SaunaStudio.security.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

/**
 * Implementace služby pro získání podrobností o uživatelském účtu na základě uživatelského jména.
 * <p>
 * Třída implementuje rozhraní {@link org.springframework.security.core.userdetails.UserDetailsService}
 * a poskytuje metodu pro načtení uživatelských údajů z databáze na základě uživatelského jména.
 * </p>
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final CustomerDao customerDao;
    private final AdminDao adminDao;

    /**
     * Vytváří instanci {@link UserDetailsService}.
     *
     * @param customerDao DAO pro správu zákazníků
     * @param adminDao DAO pro správu administrátorů
     */
    @Autowired
    public UserDetailsService(CustomerDao customerDao, AdminDao adminDao) {
        this.customerDao = customerDao;
        this.adminDao = adminDao;
    }

    /**
     * Načte uživatelské podrobnosti na základě uživatelského jména.
     * <p>
     * Pokud je uživatel nalezen jako zákazník, vrátí instanci {@link UserDetails} s rolí {@code ROLE_CUSTOMER}.
     * Pokud je uživatel nalezen jako administrátor, vrátí instanci {@link UserDetails} s rolí {@code ROLE_ADMIN}.
     * Pokud uživatel není nalezen, vyvolá {@link UsernameNotFoundException}.
     * </p>
     *
     * @param username Uživatelské jméno hledaného uživatele
     * @return Podrobnosti o uživatelském účtu
     * @throws UsernameNotFoundException Pokud uživatel s daným uživatelským jménem nebyl nalezen
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = customerDao.findByUsername(username);
        if (employee != null) {
            return new UserDetails(employee, getAuthorities("ROLE_CUSTOMER"));
        }

        Admin admin = adminDao.findByUsername(username);
        if (admin != null) {
            return new UserDetails(admin, getAuthorities("ROLE_ADMIN"));
        }

        throw new UsernameNotFoundException("User with username: " + username + " not found.");
    }

    /**
     * Vytváří kolekci rolí pro daného uživatele.
     * <p>
     * Tato metoda vrací kolekci obsahující jednu roli na základě zadaného názvu role.
     * </p>
     *
     * @param role Název role
     * @return Kolekce {@link GrantedAuthority} obsahující jednu roli
     */
    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
