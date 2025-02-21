package cz.cvut.fel.nss.SaunaStudio.dao;

import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object (DAO) pro entitu {@link SaunaStudio}.
 * Tato třída poskytuje metody pro přístup a manipulaci se studii saun
 * v databázi prostřednictvím JPA.
 */
@Repository
public class SaunaStudioDao extends BaseDao<SaunaStudio> {

    /**
     * Konstruktor pro inicializaci {@link SaunaStudioDao} s entitou {@link SaunaStudio}.
     */
    protected SaunaStudioDao() {
        super(SaunaStudio.class);
    }

    /**
     * Vyhledává studia saun na základě jejich názvu.
     *
     * <p>Pokud neexistuje žádné studio se zadaným názvem, metoda vrací {@code null}.</p>
     *
     * @param name Název studia saun
     * @return Studio saun s daným názvem nebo {@code null}, pokud žádné neexistuje
     */
    public SaunaStudio findByName(String name) {
        try {
            return em.createNamedQuery("SaunaStudio.findByName", SaunaStudio.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Vyhledává studia saun na základě jejich URL.
     *
     * <p>Pokud neexistuje žádné studio se zadaným URL, metoda vrací {@code null}.</p>
     *
     * @param url URL studia saun
     * @return Studio saun s daným URL nebo {@code null}, pokud žádné neexistuje
     */
    public SaunaStudio findByURL(String url) {
        try {
            return em.createNamedQuery("SaunaStudio.findByURL", SaunaStudio.class)
                    .setParameter("URL", url)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
