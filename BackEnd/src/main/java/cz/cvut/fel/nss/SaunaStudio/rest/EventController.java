package cz.cvut.fel.nss.SaunaStudio.rest;

import cz.cvut.fel.nss.SaunaStudio.bo.CalendarCriteriaBO;
import cz.cvut.fel.nss.SaunaStudio.bo.EventBO;
import cz.cvut.fel.nss.SaunaStudio.dto.CalendarCriteriaDTO;
import cz.cvut.fel.nss.SaunaStudio.dto.EventDTO;
import cz.cvut.fel.nss.SaunaStudio.mapper.CalendarCriteriaMapper;
import cz.cvut.fel.nss.SaunaStudio.mapper.EventMapper;
import cz.cvut.fel.nss.SaunaStudio.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * REST kontroler pro operace spojené s událostmi.
 * <p>
 * Tento kontroler poskytuje koncové body pro získávání všech dostupných událostí,
 * vyhledávání událostí na základě kritérií kalendáře a vytváření nových událostí.
 * </p>
 */
@RestController
@RequestMapping(value = "/event")
public class EventController {

    private final EventService eventService;
    private final CalendarCriteriaMapper calendarCriteriaMapper;
    private final EventMapper eventMapper;

    /**
     * Konstruktor {@link EventController} s uvedenými službami a mapery.
     *
     * @param eventService Služba pro správu událostí
     * @param eventMapper Maper pro převod {@link EventDTO} na {@link EventBO}
     * @param calendarCriteriaMapper Maper pro převod {@link CalendarCriteriaDTO} na {@link CalendarCriteriaBO}
     */
    @Autowired
    public EventController(EventService eventService, EventMapper eventMapper, CalendarCriteriaMapper calendarCriteriaMapper) {
        this.eventMapper = eventMapper;
        this.eventService = eventService;
        this.calendarCriteriaMapper = calendarCriteriaMapper;
    }

    /**
     * Získává všechny dostupné události.
     *
     * <p>
     * Tento koncový bod vrací seznam všech událostí, které jsou k dispozici.
     * </p>
     *
     * @return Seznam {@link Event} entit
     */
    @GetMapping()
    public List<Event> getAllEvents() {
        return eventService.findAllAvailable();
    }

    /**
     * Získává události na základě zadaných kritérií kalendáře.
     *
     * <p>
     * Tento koncový bod vyhledává události podle zadaných kritérií kalendáře
     * a vrací seznam odpovídajících událostí.
     * </p>
     *
     * @param calendarCriteriaDTO Data transfer objekt obsahující kritéria kalendáře
     * @return Seznam {@link Event} entit odpovídajících kritériím
     */
    @PostMapping("/criteria")
    public List<Event> getEventsByCriteria(@RequestBody CalendarCriteriaDTO calendarCriteriaDTO) {
        return eventService.findEventsByCalendarCriteria(calendarCriteriaMapper.calendarCriteriaDtoToCalendarCriteriaBo(calendarCriteriaDTO));
    }

    /**
     * Vytváří novou událost.
     *
     * <p>
     * Tento koncový bod vytvoří novou událost na základě poskytnutého {@link EventDTO}.
     * Přístup je omezen pouze na administrátory.
     * </p>
     *
     * @param eventDTO Data transfer objekt obsahující údaje o události
     * @return Vytvořená {@link Event} entita
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Event create(@RequestBody EventDTO eventDTO) {
        Objects.requireNonNull(eventDTO);
        return eventService.createEvent(eventMapper.eventDtoToEventBo(eventDTO));
    }
}
