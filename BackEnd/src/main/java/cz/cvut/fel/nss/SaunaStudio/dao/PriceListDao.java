package cz.cvut.fel.nss.SaunaStudio.dao;

import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object (DAO) pro entitu {@link PriceList}.
 * Tato třída poskytuje metody pro přístup a manipulaci s cenovými listy
 * v databázi prostřednictvím JPA.
 */
@Repository
public class PriceListDao extends BaseDao<PriceList> {

    /**
     * Konstruktor pro inicializaci {@link PriceListDao} s entitou {@link PriceList}.
     */
    protected PriceListDao() {
        super(PriceList.class);
    }

    /**
     * Vyhledává cenový list na základě zadaného studia sauny.
     *
     * <p>Pokud pro dané studio sauny neexistuje žádný cenový list, metoda vrací {@code null}.</p>
     *
     * @param studio Studio sauny, pro které se hledá cenový list
     * @return Cenový list pro dané studio sauny nebo {@code null}, pokud neexistuje
     */
    public PriceList findBySaunaStudio(SaunaStudio studio) {
        try {
            return em.createNamedQuery("PriceList.findBySaunaStudio", PriceList.class)
                    .setParameter("saunaStudio", studio)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
