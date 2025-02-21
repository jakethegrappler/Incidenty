package cz.cvut.fel.nss.SaunaStudio.mapper;

import cz.cvut.fel.nss.SaunaStudio.bo.CalendarCriteriaBO;
import cz.cvut.fel.nss.SaunaStudio.dao.criteria.CalendarCriteria;
import cz.cvut.fel.nss.SaunaStudio.dto.CalendarCriteriaDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-08T00:12:05+0200",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class CalendarCriteriaMapperImpl implements CalendarCriteriaMapper {

    @Override
    public CalendarCriteriaBO calendarCriteriaToCalendarCriteriaBO(CalendarCriteria calendarCriteria) {
        if ( calendarCriteria == null ) {
            return null;
        }

        CalendarCriteriaBO calendarCriteriaBO = new CalendarCriteriaBO();

        if ( calendarCriteria.getDate() != null ) {
            calendarCriteriaBO.setDate( DateTimeFormatter.ISO_LOCAL_DATE.format( calendarCriteria.getDate() ) );
        }
        calendarCriteriaBO.setStudio( calendarCriteria.getStudio() );

        return calendarCriteriaBO;
    }

    @Override
    public CalendarCriteriaDTO calendarCriteriaBoToCalendarCriteriaDto(CalendarCriteriaBO calendarCriteriaBO) {
        if ( calendarCriteriaBO == null ) {
            return null;
        }

        CalendarCriteriaDTO calendarCriteriaDTO = new CalendarCriteriaDTO();

        calendarCriteriaDTO.setDate( calendarCriteriaBO.getDate() );
        calendarCriteriaDTO.setStudio( calendarCriteriaBO.getStudio() );

        return calendarCriteriaDTO;
    }

    @Override
    public CalendarCriteriaBO calendarCriteriaDtoToCalendarCriteriaBo(CalendarCriteriaDTO calendarCriteriaDTO) {
        if ( calendarCriteriaDTO == null ) {
            return null;
        }

        CalendarCriteriaBO calendarCriteriaBO = new CalendarCriteriaBO();

        calendarCriteriaBO.setDate( calendarCriteriaDTO.getDate() );
        calendarCriteriaBO.setStudio( calendarCriteriaDTO.getStudio() );

        return calendarCriteriaBO;
    }

    @Override
    public CalendarCriteria calendarCriteriaBoToCalendarCriteria(CalendarCriteriaBO calendarCriteriaBO) {
        if ( calendarCriteriaBO == null ) {
            return null;
        }

        LocalDate date = null;
        SaunaStudio studio = null;

        if ( calendarCriteriaBO.getDate() != null ) {
            date = LocalDate.parse( calendarCriteriaBO.getDate() );
        }
        studio = calendarCriteriaBO.getStudio();

        CalendarCriteria calendarCriteria = new CalendarCriteria( date, studio );

        return calendarCriteria;
    }
}
