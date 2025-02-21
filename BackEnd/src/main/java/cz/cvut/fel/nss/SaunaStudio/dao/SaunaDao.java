package cz.cvut.fel.nss.SaunaStudio.dao;

import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data Access Object (DAO) pro entitu {@link Sauna}.
 * Tato třída poskytuje metody pro přístup a manipulaci se saunami
 * v databázi prostřednictvím JPA.
 */
@Repository
public class SaunaDao extends BaseDao<Sauna> {

    /**
     * Konstruktor pro inicializaci {@link SaunaDao} s entitou {@link Sauna}.
     */
    protected SaunaDao() {
        super(Sauna.class);
    }

    /**
     * Vyhledává sauny na základě zadaného studia sauny.
     *
     * <p>Pokud neexistují žádné sauny pro zadané studio, metoda vrací {@code null}.</p>
     *
     * @param studio Studio sauny, pro které se hledají sauny
     * @return Seznam saun pro dané studio nebo {@code null}, pokud žádné neexistují
     */
    public List<Sauna> findBySaunaStudio(SaunaStudio studio) {
        try {
            return em.createNamedQuery("Sauna.findBySaunaStudio", Sauna.class)
                    .setParameter("saunaStudio", studio)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Vyhledává saunu na základě jejího názvu.
     *
     * <p>Pokud neexistuje žádná sauna s daným názvem, metoda vrací {@code null}.</p>
     *
     * @param name Název sauny
     * @return Sauna s daným názvem nebo {@code null}, pokud žádná neexistuje
     */
    public Sauna findByName(String name) {
        try {
            return em.createNamedQuery("Sauna.findByName", Sauna.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Vyhledává sauny na základě typu sauny.
     *
     * <p>Pokud neexistují žádné sauny pro zadaný typ, metoda vrací {@code null}.</p>
     *
     * @param type Typ sauny
     * @return Seznam saun pro daný typ nebo {@code null}, pokud žádné neexistují
     */
    public List<Sauna> findBySaunaType(SaunaType type) {
        try {
            return em.createNamedQuery("Sauna.findBySaunaType", Sauna.class)
                    .setParameter("saunaType", type)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Vyhledává sauny na základě maximální teploty.
     *
     * <p>Pokud neexistují žádné sauny, které splňují zadanou maximální teplotu, metoda vrací {@code null}.</p>
     *
     * @param maxTemperature Maximální teplota sauny
     * @return Seznam saun, které odpovídají maximální teplotě nebo {@code null}, pokud žádné neexistují
     */
    public List<Sauna> findByMaxTemperature(Integer maxTemperature) {
        try {
            return em.createNamedQuery("Sauna.findByMaxTemperature", Sauna.class)
                    .setParameter("maxTemperature", maxTemperature)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
