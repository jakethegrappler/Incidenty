package cz.cvut.fel.nss.SaunaStudio.service;

import cz.cvut.fel.nss.SaunaStudio.bo.AdminBO;
import cz.cvut.fel.nss.SaunaStudio.dao.AdminDao;
import cz.cvut.fel.nss.SaunaStudio.exception.MyException;
import cz.cvut.fel.nss.SaunaStudio.mapper.AdminMapper;
import cz.cvut.fel.nss.SaunaStudio.model.Admin;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Služba pro správu administrátorů.
 * <p>
 * Třída poskytuje metody pro vyhledávání administrátorů, vytváření nových administrátorů a další operace
 * spojené s administrátory. Všechny operace jsou zabezpečeny tak, že je může vykonávat pouze uživatel s rolí ADMIN.
 * </p>
 */
@Service
public class AdminService {

    private final PasswordEncoder passwordEncoder;
    private final AdminDao adminDao;
    private final AdminMapper adminMapper;

    /**
     * Vytváří instanci {@link AdminService}.
     *
     * @param passwordEncoder Kódovač hesel pro šifrování hesel administrátorů
     * @param adminDao DAO pro správu administrátorů
     * @param adminMapper Mapa pro převod mezi {@link AdminBO} a {@link Admin}
     */
    @Autowired
    public AdminService(PasswordEncoder passwordEncoder, AdminDao adminDao, AdminMapper adminMapper) {
        this.passwordEncoder = passwordEncoder;
        this.adminDao = adminDao;
        this.adminMapper = adminMapper;
    }

    /**
     * Vyhledá administrátora podle jeho ID.
     * <p>
     * Tato metoda je zabezpečena tak, že ji může vykonávat pouze uživatel s rolí ADMIN.
     * </p>
     *
     * @param id ID administrátora
     * @return {@link Admin} administrátor s daným ID
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public Admin find(Integer id) {
        return adminDao.find(id);
    }

    /**
     * Vyhledá administrátora podle jeho uživatelského jména.
     * <p>
     * Tato metoda je zabezpečena tak, že ji může vykonávat pouze uživatel s rolí ADMIN.
     * </p>
     *
     * @param username Uživatelské jméno administrátora
     * @return {@link Admin} administrátor s daným uživatelským jménem
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public Admin findByUsername(String username) {
        return adminDao.findByUsername(username);
    }

    /**
     * Vytvoří nového administrátora.
     * <p>
     * Tato metoda zkontroluje, zda uživatelské jméno již není použito. Pokud ano, vyvolá {@link MyException}.
     * Heslo administrátora je šifrováno pomocí {@link PasswordEncoder}. Poté je administrátor uložen do databáze.
     * </p>
     *
     * @param adminBO Business objekt obsahující informace o administrátorovi
     * @return Vytvořený {@link Admin}
     * @throws MyException Pokud uživatelské jméno již existuje
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public Admin create(AdminBO adminBO) {
        Objects.requireNonNull(adminBO);
        Admin admin = adminMapper.adminBoToAdmin(adminBO);
        if (adminDao.findByUsername(adminBO.getUsername()) != null) {
            throw new MyException("Username already taken.");
        }

        admin.encodePassword(passwordEncoder);

        adminDao.persist(admin);
        return admin;
    }
}
