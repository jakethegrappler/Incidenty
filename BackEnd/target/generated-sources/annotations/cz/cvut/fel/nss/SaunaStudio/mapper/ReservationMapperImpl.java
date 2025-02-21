package cz.cvut.fel.nss.SaunaStudio.mapper;

import cz.cvut.fel.nss.SaunaStudio.bo.ReservationBO;
import cz.cvut.fel.nss.SaunaStudio.dto.ReservationDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-08T00:12:05+0200",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class ReservationMapperImpl implements ReservationMapper {

    @Override
    public ReservationBO reservationToReservationBO(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }

        ReservationBO reservationBO = new ReservationBO();

        reservationBO.setEvent( reservation.getEvent() );
        reservationBO.setEndTime( reservation.getEndTime() );
        if ( reservation.getNumOfPeople() != null ) {
            reservationBO.setNumOfPeople( reservation.getNumOfPeople() );
        }
        reservationBO.setStartTime( reservation.getStartTime() );
        if ( reservation.getStateType() != null ) {
            reservationBO.setStateType( reservation.getStateType().name() );
        }
        reservationBO.setSaunaStudio( reservation.getSaunaStudio() );
        reservationBO.setEmployee( reservation.getEmployee() );

        return reservationBO;
    }

    @Override
    public ReservationDTO reservationBoToReservationDto(ReservationBO reservationBO) {
        if ( reservationBO == null ) {
            return null;
        }

        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setEvent( reservationBO.getEvent() );
        if ( reservationBO.getEndTime() != null ) {
            reservationDTO.setEndTime( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( reservationBO.getEndTime() ) );
        }
        reservationDTO.setNumOfPeople( reservationBO.getNumOfPeople() );
        if ( reservationBO.getStartTime() != null ) {
            reservationDTO.setStartTime( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( reservationBO.getStartTime() ) );
        }
        reservationDTO.setStateType( reservationBO.getStateType() );
        reservationDTO.setSaunaStudio( reservationBO.getSaunaStudio() );
        reservationDTO.setEmployee( reservationBO.getEmployee() );

        return reservationDTO;
    }

    @Override
    public ReservationBO reservationDtoToReservationBo(ReservationDTO reservationDTO) {
        if ( reservationDTO == null ) {
            return null;
        }

        ReservationBO reservationBO = new ReservationBO();

        reservationBO.setEvent( reservationDTO.getEvent() );
        if ( reservationDTO.getEndTime() != null ) {
            reservationBO.setEndTime( LocalDateTime.parse( reservationDTO.getEndTime() ) );
        }
        reservationBO.setNumOfPeople( reservationDTO.getNumOfPeople() );
        if ( reservationDTO.getStartTime() != null ) {
            reservationBO.setStartTime( LocalDateTime.parse( reservationDTO.getStartTime() ) );
        }
        reservationBO.setStateType( reservationDTO.getStateType() );
        reservationBO.setSaunaStudio( reservationDTO.getSaunaStudio() );
        reservationBO.setEmployee( reservationDTO.getEmployee() );

        return reservationBO;
    }

    @Override
    public Reservation reservationBoToReservation(ReservationBO reservationBO) {
        if ( reservationBO == null ) {
            return null;
        }

        Reservation reservation = new Reservation();

        reservation.setStartTime( reservationBO.getStartTime() );
        reservation.setEndTime( reservationBO.getEndTime() );
        reservation.setNumOfPeople( reservationBO.getNumOfPeople() );
        if ( reservationBO.getStateType() != null ) {
            reservation.setStateType( Enum.valueOf( ReservationStateType.class, reservationBO.getStateType() ) );
        }
        reservation.setEmployee( reservationBO.getEmployee() );
        reservation.setEvent( reservationBO.getEvent() );
        reservation.setSaunaStudio( reservationBO.getSaunaStudio() );

        return reservation;
    }
}
