package cz.cvut.fel.nss.SaunaStudio.dao.criteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Užitečné třída pro vytváření predikátů pro kritéria vyhledávání v kalendáři.
 * Tato třída poskytuje statické metody pro vytváření predikátů, které mohou být použity
 * v dotazech JPA Criteria API pro filtrování výsledků podle zadaných kritérií.
 */
public class CalendarCriteriaUtils {

    /**
     * Vytváří predikát pro filtrování událostí podle zadaného data.
     *
     * <p>Predikát je založen na tom, zda je zadané datum a používá se k filtrování událostí
     * na základě toho, zda jejich startovní čas spadá do daného dne.</p>
     *
     * @param cb {@link CriteriaBuilder} pro vytváření podmínek v dotazu.
     * @param root {@link Root} entit, které se používají k dotazování.
     * @param criteria {@link CalendarCriteria} obsahující datum pro filtrování.
     * @return {@link Predicate} pro filtrování událostí podle zadaného data.
     */
    public static Predicate buildDatePredicate(CriteriaBuilder cb, Root<?> root, CalendarCriteria criteria) {
        Predicate predicate = cb.conjunction();
        if (criteria.getDate() != null) {
            LocalDateTime startOfDay = criteria.getDate().atStartOfDay();
            LocalDateTime endOfDay = criteria.getDate().atTime(LocalTime.MAX);
            predicate = cb.and(predicate,
                    cb.between(root.get("startTime"), startOfDay, endOfDay));
        }
        return predicate;
    }
}
