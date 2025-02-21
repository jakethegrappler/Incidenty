package cz.cvut.fel.nss.SaunaStudio.dao.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Kritéria pro vyhledávání v kalendáři.
 * Tato třída slouží k definování kritérií pro vyhledávání událostí v kalendáři
 * na základě data a sauny.
 */
@Getter
@Setter
@AllArgsConstructor
public class CalendarCriteria {

    /**
     * Datum, které se používá pro vyhledávání událostí v kalendáři.
     */
    private LocalDate date;

    /**
     * Sauna studio, ve kterém se hledají události.
     */
    private SaunaStudio studio;
}
