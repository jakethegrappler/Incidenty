package cz.cvut.fel.nss.SaunaStudio.service;

import cz.cvut.fel.nss.SaunaStudio.bo.CalendarCriteriaBO;
import cz.cvut.fel.nss.SaunaStudio.bo.EventBO;
import cz.cvut.fel.nss.SaunaStudio.dao.EventDao;
import cz.cvut.fel.nss.SaunaStudio.mapper.CalendarCriteriaMapper;
import cz.cvut.fel.nss.SaunaStudio.mapper.EventMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Služba pro správu událostí.
 * <p>
 * Třída poskytuje metody pro získávání událostí, vytváření nových událostí a vyhledávání událostí podle kritérií kalendáře.
 * </p>
 */
@Service
public class EventService {

    private final EventDao eventDao;
    private final EventMapper eventMapper;
    private final CalendarCriteriaMapper calendarCriteriaMapper;

    /**
     * Vytváří instanci {@link EventService}.
     *
     * @param eventDao DAO pro správu událostí
     * @param eventMapper Mapa pro převod mezi {@link EventBO} a {@link Event}
     * @param calendarCriteriaMapper Mapa pro převod mezi {@link CalendarCriteriaBO} a kalendářovými kritérii
     */
    @Autowired
    public EventService(EventDao eventDao, EventMapper eventMapper, CalendarCriteriaMapper calendarCriteriaMapper) {
        this.eventDao = eventDao;
        this.eventMapper = eventMapper;
        this.calendarCriteriaMapper = calendarCriteriaMapper;
    }

    /**
     * Získá všechny události.
     *
     * @return List všech {@link Event} událostí
     */
    @Transactional
    public List<Event> findAll() {
        return eventDao.findAll();
    }

    /**
     * Získá všechny dostupné události.
     *
     * @return List dostupných {@link Event} událostí
     */
    @Transactional
    public List<Event> findAllAvailable() {
        return eventDao.findAllAvailable();
    }

    /**
     * Vyhledá události podle kritérií kalendáře.
     *
     * @param criteriaBO Business objekt obsahující kritéria kalendáře
     * @return List {@link Event} událostí odpovídajících kritériím
     */
    @Transactional
    public List<Event> findEventsByCalendarCriteria(CalendarCriteriaBO criteriaBO) {
        return eventDao.findEventsByCalendarCriteria(calendarCriteriaMapper.calendarCriteriaBoToCalendarCriteria(criteriaBO));
    }

    /**
     * Vytvoří novou událost.
     * <p>
     * Tato metoda uloží novou událost do databáze.
     * </p>
     *
     * @param eventBO Business objekt obsahující informace o události
     * @return Vytvořená {@link Event} událost
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public Event createEvent(EventBO eventBO) {
        Objects.requireNonNull(eventBO);
        Event event = eventMapper.eventBoToEvent(eventBO);
        eventDao.persist(event);
        return event;
    }
}
