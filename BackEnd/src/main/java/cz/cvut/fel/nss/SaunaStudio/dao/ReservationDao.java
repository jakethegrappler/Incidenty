package cz.cvut.fel.nss.SaunaStudio.dao;

import cz.cvut.fel.nss.SaunaStudio.dao.criteria.CalendarCriteria;
import cz.cvut.fel.nss.SaunaStudio.dao.criteria.CalendarCriteriaUtils;
import cz.cvut.fel.nss.SaunaStudio.dto.HourlyOccupancy;
import cz.cvut.fel.nss.SaunaStudio.model.Employee;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) pro entitu {@link Reservation}.
 * Tato třída poskytuje metody pro přístup a manipulaci s rezervacemi
 * v databázi prostřednictvím JPA.
 */
@Repository
public class ReservationDao extends BaseDao<Reservation> {

    /**
     * Konstruktor pro inicializaci {@link ReservationDao} s entitou {@link Reservation}.
     */
    protected ReservationDao() {
        super(Reservation.class);
    }

    /**
     * Vyhledává rezervace na základě zákazníka.
     *
     * <p>Pokud neexistují žádné rezervace pro zadaného zákazníka, metoda vrací {@code null}.</p>
     *
     * @param employee Zákazník, jehož rezervace se hledají
     * @return Seznam rezervací pro daného zákazníka nebo {@code null}, pokud žádné neexistují
     */
    public List<Reservation> findByCustomer(Employee employee) {
        try {
            return em.createNamedQuery("Reservation.findByCustomer", Reservation.class)
                    .setParameter("customer", employee)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Vyhledává rezervace na základě dne.
     *
     * <p>Pokud neexistují žádné rezervace pro zadaný den, metoda vrací {@code null}.</p>
     *
     * @param day Den, pro který se hledají rezervace
     * @return Seznam rezervací pro daný den nebo {@code null}, pokud žádné neexistují
     */
    public List<Reservation> findByDay(LocalDate day) {
        try {
            return em.createNamedQuery("Reservation.findByDay", Reservation.class)
                    .setParameter("day", day)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Vyhledává rezervace na základě hodiny.
     *
     * <p>Pokud neexistují žádné rezervace pro zadanou hodinu, metoda vrací {@code null}.</p>
     *
     * @param hour Hodina, pro kterou se hledají rezervace
     * @return Seznam rezervací pro danou hodinu nebo {@code null}, pokud žádné neexistují
     */
    public List<Reservation> findByHour(LocalDateTime hour) {
        try {
            return em.createNamedQuery("Reservation.findByHour", Reservation.class)
                    .setParameter("hour", hour)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Získává hodinovou obsazenost na základě zadaných kritérií.
     *
     * <p>Vrací seznam {@link HourlyOccupancy} objektů, které obsahují hodinovou obsazenost na základě rezervací.</p>
     *
     * @param criteria Kritéria pro filtrování rezervací
     * @return Seznam hodinové obsazenosti
     */
    public List<HourlyOccupancy> findHourlyOccupancyByCriteria(CalendarCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createTupleQuery();
        Root<Reservation> reservationRoot = query.from(Reservation.class);

        query.multiselect(
                cb.function("HOUR", Integer.class, reservationRoot.get("startTime")).alias("hour"),
                cb.function("HOUR", Integer.class, reservationRoot.get("endTime")).alias("endHour"),
                cb.sum(reservationRoot.get("numOfPeople")).alias("totalOccupancy")
        );

        query.groupBy(
                cb.function("HOUR", Integer.class, reservationRoot.get("startTime")),
                cb.function("HOUR", Integer.class, reservationRoot.get("endTime"))
        );

        Predicate predicate = buildPredicate(cb, reservationRoot, criteria);
        query.where(predicate);
        query.orderBy(cb.asc(cb.function("HOUR", Integer.class, reservationRoot.get("startTime"))));
        List<Tuple> result = em.createQuery(query).getResultList();
        return fillHourlyOccupancy(result);
    }

    /**
     * Transformuje výsledky dotazu na seznam {@link HourlyOccupancy} objektů.
     *
     * @param result Výsledky dotazu jako seznam {@link Tuple} objektů
     * @return Seznam hodinové obsazenosti
     */
    private List<HourlyOccupancy> fillHourlyOccupancy(List<Tuple> result) {
        List<HourlyOccupancy> hourlyOccupancies = new ArrayList<>();
        for (Tuple tuple : result) {
            Integer startHour = tuple.get("hour", Integer.class);
            Integer endHour = tuple.get("endHour", Integer.class);
            Long totalOccupancy = tuple.get("totalOccupancy", Long.class);

            // Vytvoření HourlyOccupancy objektů pro každou hodinu rezervace
            for (int hour = startHour; hour <= endHour; hour++) {
                hourlyOccupancies.add(new HourlyOccupancy(LocalTime.of(hour, 0), totalOccupancy.intValue()));
            }
        }
        return hourlyOccupancies;
    }

    /**
     * Vytváří predikát pro filtrování rezervací na základě kritérií.
     *
     * @param cb CriteriaBuilder pro vytváření dotazů
     * @param root Kořenová entita pro dotaz
     * @param criteria Kritéria pro filtrování rezervací
     * @return Predikát pro filtrování rezervací
     */
    private Predicate buildPredicate(CriteriaBuilder cb, Root<Reservation> root, CalendarCriteria criteria) {
        Predicate predicate = CalendarCriteriaUtils.buildDatePredicate(cb, root, criteria);

        if (criteria.getStudio() != null) {
            predicate = cb.and(predicate,
                    cb.equal(root.get("studio"), criteria.getStudio()));
        }

        predicate = cb.and(predicate,
                cb.notEqual(root.get("stateType"), ReservationStateType.CANCELED));

        return predicate;
    }
}
