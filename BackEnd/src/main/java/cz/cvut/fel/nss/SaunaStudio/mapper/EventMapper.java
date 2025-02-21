package cz.cvut.fel.nss.SaunaStudio.mapper;

import cz.cvut.fel.nss.SaunaStudio.bo.EventBO;
import cz.cvut.fel.nss.SaunaStudio.dto.EventDTO;
import org.mapstruct.Mapper;

/**
 * Mapper pro převod mezi entitami {@link Event}, byznys objekty {@link EventBO} a datovými přenosovými objekty {@link EventDTO}.
 *
 * <p>Tento mapper převádí data mezi různými vrstvami aplikace: mezi entitami, byznys objekty a datovými přenosovými objekty.</p>
 */
@Mapper(componentModel = "spring")
public interface EventMapper {

    /**
     * Převádí entitu {@link Event} na byznys objekt {@link EventBO}.
     *
     * @param event Entita události, kterou je třeba převést
     * @return Byznys objekt události odpovídající zadané entitě
     */
    EventBO eventToEventBO(Event event);

    /**
     * Převádí byznys objekt {@link EventBO} na datový přenosový objekt {@link EventDTO}.
     *
     * @param eventBO Byznys objekt události, který je třeba převést
     * @return Datový přenosový objekt události odpovídající zadanému byznys objektu
     */
    EventDTO eventBoToEventDto(EventBO eventBO);

    /**
     * Převádí datový přenosový objekt {@link EventDTO} na byznys objekt {@link EventBO}.
     *
     * @param eventDTO Datový přenosový objekt události, který je třeba převést
     * @return Byznys objekt události odpovídající zadanému datovému přenosovému objektu
     */
    EventBO eventDtoToEventBo(EventDTO eventDTO);

    /**
     * Převádí byznys objekt {@link EventBO} na entitu {@link Event}.
     *
     * @param eventBO Byznys objekt události, který je třeba převést
     * @return Entita události odpovídající zadanému byznys objektu
     */
    Event eventBoToEvent(EventBO eventBO);

}
