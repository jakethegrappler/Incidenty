package cz.cvut.fel.nss.SaunaStudio.mapper;

import cz.cvut.fel.nss.SaunaStudio.bo.ReservationBO;
import cz.cvut.fel.nss.SaunaStudio.dto.ReservationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.LocalDateTime;

/**
 * Mapper pro převod mezi entitami {@link Reservation}, byznys objekty {@link ReservationBO} a datovými přenosovými objekty {@link ReservationDTO}.
 *
 * <p>Tento mapper převádí data mezi různými vrstvami aplikace: mezi entitami, byznys objekty a datovými přenosovými objekty.</p>
 */
@Mapper(componentModel = "spring")
public interface ReservationMapper {

    /**
     * Převádí entitu {@link Reservation} na byznys objekt {@link ReservationBO}.
     *
     * @param reservation Entita rezervace, kterou je třeba převést
     * @return Byznys objekt rezervace odpovídající zadané entitě
     */
    ReservationBO reservationToReservationBO(Reservation reservation);

    /**
     * Převádí byznys objekt {@link ReservationBO} na datový přenosový objekt {@link ReservationDTO}.
     *
     * @param reservationBO Byznys objekt rezervace, který je třeba převést
     * @return Datový přenosový objekt rezervace odpovídající zadanému byznys objektu
     */
    ReservationDTO reservationBoToReservationDto(ReservationBO reservationBO);

    /**
     * Převádí datový přenosový objekt {@link ReservationDTO} na byznys objekt {@link ReservationBO}.
     *
     * @param reservationDTO Datový přenosový objekt rezervace, který je třeba převést
     * @return Byznys objekt rezervace odpovídající zadanému datovému přenosovému objektu
     */
    ReservationBO reservationDtoToReservationBo(ReservationDTO reservationDTO);

    /**
     * Převádí byznys objekt {@link ReservationBO} na entitu {@link Reservation}.
     *
     * @param reservationBO Byznys objekt rezervace, který je třeba převést
     * @return Entita rezervace odpovídající zadanému byznys objektu
     */
    Reservation reservationBoToReservation(ReservationBO reservationBO);

    /**
     * Převádí {@link LocalDateTime} na {@link String}.
     *
     * @param dateTime Časový údaj, který je třeba převést na {@link String}
     * @return Řetězec představující zadaný časový údaj, nebo {@code null}, pokud je zadaný {@code null}
     */
    @Named("localDateTimeToString")
    default String localDateTimeToString(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.toString() : null;
    }

    /**
     * Převádí {@link String} na {@link LocalDateTime}.
     *
     * @param dateTime Řetězec představující časový údaj, který je třeba převést na {@link LocalDateTime}
     * @return {@link LocalDateTime} představující zadaný časový údaj, nebo {@code null}, pokud je zadaný {@code null}
     */
    @Named("stringToLocalDateTime")
    default LocalDateTime stringToLocalDateTime(String dateTime) {
        return dateTime != null ? LocalDateTime.parse(dateTime) : null;
    }
}
