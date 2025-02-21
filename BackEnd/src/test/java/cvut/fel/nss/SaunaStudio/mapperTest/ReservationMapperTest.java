package cvut.fel.nss.SaunaStudio.mapperTest;

import cz.cvut.fel.nss.SaunaStudio.bo.ReservationBO;
import cz.cvut.fel.nss.SaunaStudio.configuration.TestConfig;
import cz.cvut.fel.nss.SaunaStudio.dto.ReservationDTO;
import cz.cvut.fel.nss.SaunaStudio.mapper.ReservationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Wubba Lubba dub-dub
 */

@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class ReservationMapperTest {

    @Autowired
    private ReservationMapper reservationMapper;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Test
    public void testReservationToReservationBO() {
        Reservation reservation = new Reservation();
        reservation.setStartTime(LocalDateTime.of(2023, 9, 1, 10, 0, 0));
        reservation.setEndTime(LocalDateTime.of(2023, 9, 1, 12, 0, 0));
        reservation.setNumOfPeople(5);
        reservation.setStateType(ReservationStateType.valueOf("BOOKED"));
        reservation.setEvent(new Event());
        reservation.setSaunaStudio(new SaunaStudio());

        ReservationBO reservationBO = reservationMapper.reservationToReservationBO(reservation);

        assertEquals(reservation.getStartTime(), reservationBO.getStartTime());
        assertEquals(reservation.getEndTime(), reservationBO.getEndTime());
        assertEquals(reservation.getNumOfPeople(), reservationBO.getNumOfPeople());
        assertEquals(reservation.getStateType().toString(), reservationBO.getStateType());
        assertEquals(reservation.getEvent(), reservationBO.getEvent());
        assertEquals(reservation.getSaunaStudio(), reservationBO.getSaunaStudio());
    }

    @Test
    public void testReservationBOToReservationDTO() {
        ReservationBO reservationBO = new ReservationBO();
        reservationBO.setStartTime(LocalDateTime.of(2023, 9, 1, 10, 0));
        reservationBO.setEndTime(LocalDateTime.of(2023, 9, 1, 12, 0));
        reservationBO.setNumOfPeople(5);
        reservationBO.setStateType("BOOKED");
        reservationBO.setEvent(new Event());
        reservationBO.setSaunaStudio(new SaunaStudio());

        ReservationDTO reservationDTO = reservationMapper.reservationBoToReservationDto(reservationBO);

        assertEquals(
                LocalDateTime.parse(reservationBO.getStartTime().toString()).format(formatter),
                reservationDTO.getStartTime());
        assertEquals(
                LocalDateTime.parse(reservationBO.getEndTime().toString()).format(formatter),
                reservationDTO.getEndTime());
        assertEquals(reservationBO.getNumOfPeople(), reservationDTO.getNumOfPeople());
        assertEquals(reservationBO.getStateType(), reservationDTO.getStateType());
        assertEquals(reservationBO.getEvent(), reservationDTO.getEvent());
        assertEquals(reservationBO.getSaunaStudio(), reservationDTO.getSaunaStudio());
    }

    @Test
    public void testReservationDTOToReservationBO() {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setStartTime(LocalDateTime.of(2023, 9, 1, 10, 0).toString());
        reservationDTO.setEndTime(LocalDateTime.of(2023, 9, 1, 12, 0).toString());
        reservationDTO.setNumOfPeople(5);
        reservationDTO.setStateType("BOOKED");
        reservationDTO.setEvent(new Event());
        reservationDTO.setSaunaStudio(new SaunaStudio());

        ReservationBO reservationBO = reservationMapper.reservationDtoToReservationBo(reservationDTO);

        assertEquals(LocalDateTime.parse(reservationDTO.getStartTime()), reservationBO.getStartTime());
        assertEquals(LocalDateTime.parse(reservationDTO.getEndTime()), reservationBO.getEndTime());
        assertEquals(reservationDTO.getNumOfPeople(), reservationBO.getNumOfPeople());
        assertEquals(reservationDTO.getStateType(), reservationBO.getStateType());
        assertEquals(reservationDTO.getEvent(), reservationBO.getEvent());
        assertEquals(reservationDTO.getSaunaStudio(), reservationBO.getSaunaStudio());
    }

    @Test
    public void testReservationBOToReservation() {
        ReservationBO reservationBO = new ReservationBO();
        reservationBO.setStartTime(LocalDateTime.of(2023, 9, 1, 10, 0));
        reservationBO.setEndTime(LocalDateTime.of(2023, 9, 1, 12, 0));
        reservationBO.setNumOfPeople(5);
        reservationBO.setStateType("BOOKED");
        reservationBO.setEvent(new Event());
        reservationBO.setSaunaStudio(new SaunaStudio());

        Reservation reservation = reservationMapper.reservationBoToReservation(reservationBO);

        assertEquals(reservationBO.getStartTime(), reservation.getStartTime());
        assertEquals(reservationBO.getEndTime(), reservation.getEndTime());
        assertEquals(reservationBO.getNumOfPeople(), reservation.getNumOfPeople());
        assertEquals(reservationBO.getStateType(), reservation.getStateType().toString());
        assertEquals(reservationBO.getEvent(), reservation.getEvent());
        assertEquals(reservationBO.getSaunaStudio(), reservation.getSaunaStudio());
    }
}
