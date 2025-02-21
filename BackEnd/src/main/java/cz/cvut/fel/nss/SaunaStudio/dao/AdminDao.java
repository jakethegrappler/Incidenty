package cz.cvut.fel.nss.SaunaStudio.dao;

import cz.cvut.fel.nss.SaunaStudio.model.Admin;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object (DAO) pro entitu {@link Admin}.
 * Tato třída poskytuje metody pro přístup a manipulaci s daty adminů
 * v databázi prostřednictvím JPA.
 */
@Repository
public class AdminDao extends BaseDao<Admin> {

    /**
     * Konstruktor pro inicializaci {@link AdminDao} s entitou {@link Admin}.
     */
    protected AdminDao() {
        super(Admin.class);
    }

    /**
     * Vyhledává administrátora podle uživatelského jména.
     *
     * <p>Pokud není administrátor nalezen, metoda vrací {@code null}.</p>
     *
     * @param username Uživatelské jméno administrátora, které se má hledat.
     * @return {@link Admin} nalezený podle uživatelského jména, nebo {@code null}, pokud administrátor neexistuje.
     */
    public Admin findByUsername(String username) {
        try {
            return em.createNamedQuery("Admin.findByUsername", Admin.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
