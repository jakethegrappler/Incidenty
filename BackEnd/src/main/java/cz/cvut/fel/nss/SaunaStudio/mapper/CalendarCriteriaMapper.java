package cz.cvut.fel.nss.SaunaStudio.mapper;

import cz.cvut.fel.nss.SaunaStudio.bo.CalendarCriteriaBO;
import cz.cvut.fel.nss.SaunaStudio.dao.criteria.CalendarCriteria;
import cz.cvut.fel.nss.SaunaStudio.dto.CalendarCriteriaDTO;
import org.mapstruct.Mapper;

/**
 * Mapper pro převod mezi entitami {@link CalendarCriteria}, {@link CalendarCriteriaBO} a {@link CalendarCriteriaDTO}.
 *
 * <p>Tento mapper převádí data mezi různými vrstvami aplikace: mezi entitami, byznys objekty a datovými přenosovými objekty.</p>
 */
@Mapper(componentModel = "spring")
public interface CalendarCriteriaMapper {

    /**
     * Převádí entitu {@link CalendarCriteria} na byznys objekt {@link CalendarCriteriaBO}.
     *
     * @param calendarCriteria Entita pro kritéria kalendáře, kterou je třeba převést
     * @return Byznys objekt kalendářních kritérií odpovídající zadané entitě
     */
    CalendarCriteriaBO calendarCriteriaToCalendarCriteriaBO(CalendarCriteria calendarCriteria);

    /**
     * Převádí byznys objekt {@link CalendarCriteriaBO} na datový přenosový objekt {@link CalendarCriteriaDTO}.
     *
     * @param calendarCriteriaBO Byznys objekt kalendářních kritérií, který je třeba převést
     * @return Datový přenosový objekt kalendářních kritérií odpovídající zadanému byznys objektu
     */
    CalendarCriteriaDTO calendarCriteriaBoToCalendarCriteriaDto(CalendarCriteriaBO calendarCriteriaBO);

    /**
     * Převádí datový přenosový objekt {@link CalendarCriteriaDTO} na byznys objekt {@link CalendarCriteriaBO}.
     *
     * @param calendarCriteriaDTO Datový přenosový objekt kalendářních kritérií, který je třeba převést
     * @return Byznys objekt kalendářních kritérií odpovídající zadanému datovému přenosovému objektu
     */
    CalendarCriteriaBO calendarCriteriaDtoToCalendarCriteriaBo(CalendarCriteriaDTO calendarCriteriaDTO);

    /**
     * Převádí byznys objekt {@link CalendarCriteriaBO} na entitu {@link CalendarCriteria}.
     *
     * @param calendarCriteriaBO Byznys objekt kalendářních kritérií, který je třeba převést
     * @return Entita kalendářních kritérií odpovídající zadanému byznys objektu
     */
    CalendarCriteria calendarCriteriaBoToCalendarCriteria(CalendarCriteriaBO calendarCriteriaBO);
}
