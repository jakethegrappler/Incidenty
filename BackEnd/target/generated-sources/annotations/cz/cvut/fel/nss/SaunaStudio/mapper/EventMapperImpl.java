package cz.cvut.fel.nss.SaunaStudio.mapper;

import cz.cvut.fel.nss.SaunaStudio.bo.EventBO;
import cz.cvut.fel.nss.SaunaStudio.dto.EventDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-08T00:12:05+0200",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public EventBO eventToEventBO(Event event) {
        if ( event == null ) {
            return null;
        }

        EventBO eventBO = new EventBO();

        eventBO.setName( event.getName() );
        eventBO.setDescription( event.getDescription() );
        if ( event.getStartTime() != null ) {
            eventBO.setStartTime( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( event.getStartTime() ) );
        }
        if ( event.getEndTime() != null ) {
            eventBO.setEndTime( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( event.getEndTime() ) );
        }
        eventBO.setTotalCapacity( event.getTotalCapacity() );
        eventBO.setFreeCapacity( event.getFreeCapacity() );
        eventBO.setIsAvailable( event.getIsAvailable() );
        eventBO.setImgFolderURL( event.getImgFolderURL() );
        List<SaunaEvent> list = event.getSaunaEvents();
        if ( list != null ) {
            eventBO.setSaunaEvents( new ArrayList<SaunaEvent>( list ) );
        }

        return eventBO;
    }

    @Override
    public EventDTO eventBoToEventDto(EventBO eventBO) {
        if ( eventBO == null ) {
            return null;
        }

        EventDTO eventDTO = new EventDTO();

        eventDTO.setName( eventBO.getName() );
        eventDTO.setDescription( eventBO.getDescription() );
        eventDTO.setStartTime( eventBO.getStartTime() );
        eventDTO.setEndTime( eventBO.getEndTime() );
        eventDTO.setTotalCapacity( eventBO.getTotalCapacity() );
        eventDTO.setFreeCapacity( eventBO.getFreeCapacity() );
        eventDTO.setIsAvailable( eventBO.getIsAvailable() );
        eventDTO.setImgFolderURL( eventBO.getImgFolderURL() );
        List<SaunaEvent> list = eventBO.getSaunaEvents();
        if ( list != null ) {
            eventDTO.setSaunaEvents( new ArrayList<SaunaEvent>( list ) );
        }

        return eventDTO;
    }

    @Override
    public EventBO eventDtoToEventBo(EventDTO eventDTO) {
        if ( eventDTO == null ) {
            return null;
        }

        EventBO eventBO = new EventBO();

        eventBO.setName( eventDTO.getName() );
        eventBO.setDescription( eventDTO.getDescription() );
        eventBO.setStartTime( eventDTO.getStartTime() );
        eventBO.setEndTime( eventDTO.getEndTime() );
        eventBO.setTotalCapacity( eventDTO.getTotalCapacity() );
        eventBO.setFreeCapacity( eventDTO.getFreeCapacity() );
        eventBO.setIsAvailable( eventDTO.getIsAvailable() );
        eventBO.setImgFolderURL( eventDTO.getImgFolderURL() );
        List<SaunaEvent> list = eventDTO.getSaunaEvents();
        if ( list != null ) {
            eventBO.setSaunaEvents( new ArrayList<SaunaEvent>( list ) );
        }

        return eventBO;
    }

    @Override
    public Event eventBoToEvent(EventBO eventBO) {
        if ( eventBO == null ) {
            return null;
        }

        Event event = new Event();

        event.setName( eventBO.getName() );
        event.setDescription( eventBO.getDescription() );
        if ( eventBO.getStartTime() != null ) {
            event.setStartTime( LocalDateTime.parse( eventBO.getStartTime() ) );
        }
        if ( eventBO.getEndTime() != null ) {
            event.setEndTime( LocalDateTime.parse( eventBO.getEndTime() ) );
        }
        event.setTotalCapacity( eventBO.getTotalCapacity() );
        event.setFreeCapacity( eventBO.getFreeCapacity() );
        event.setIsAvailable( eventBO.getIsAvailable() );
        event.setImgFolderURL( eventBO.getImgFolderURL() );
        List<SaunaEvent> list = eventBO.getSaunaEvents();
        if ( list != null ) {
            event.setSaunaEvents( new ArrayList<SaunaEvent>( list ) );
        }

        return event;
    }
}
