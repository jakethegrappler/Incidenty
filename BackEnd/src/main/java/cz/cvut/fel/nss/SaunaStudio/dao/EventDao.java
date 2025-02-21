package cz.cvut.fel.nss.SaunaStudio.dao;

import cz.cvut.fel.nss.SaunaStudio.dao.criteria.CalendarCriteriaUtils;
import cz.cvut.fel.nss.SaunaStudio.dao.criteria.CalendarCriteria;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data Access Object (DAO) pro entitu {@link Event}.
 * Tato třída poskytuje metody pro přístup a manipulaci s událostmi
 * v databázi prostřednictvím JPA.
 */
@Repository
public class EventDao extends BaseDao<Event> {

    /**
     * Konstruktor pro inicializaci {@link EventDao} s entitou {@link Event}.
     */
    protected EventDao() {
        super(Event.class);
    }

    /**
     * Vyhledává všechny dostupné události.
     *
     * <p>Pokud nejsou žádné dostupné události, metoda vrací {@code null}.</p>
     *
     * @return Seznam dostupných událostí.
     */
    public List<Event> findAllAvailable() {
        try {
            return em.createNamedQuery("Event.findAllAvailable", Event.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Vyhledává události na základě zadaných kritérií.
     *
     * <p>Vyhledávání probíhá na základě data a, pokud je specifikováno, studia.</p>
     *
     * @param criteria Kritéria pro vyhledávání událostí.
     * @return Seznam událostí odpovídajících zadaným kritériím.
     */
    public List<Event> findEventsByCalendarCriteria(CalendarCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Event> query = cb.createQuery(Event.class);
        Root<Event> eventRoot = query.from(Event.class);

        Predicate predicate = CalendarCriteriaUtils.buildDatePredicate(cb, eventRoot, criteria);

        if (criteria.getStudio() != null) {
            Join<Event, Sauna> saunaJoin = eventRoot.join("sauna");
            predicate = cb.and(predicate,
                    cb.equal(saunaJoin.get("studio"), criteria.getStudio()));
        }

        query.select(eventRoot).where(predicate);

        return em.createQuery(query).getResultList();
    }
}
