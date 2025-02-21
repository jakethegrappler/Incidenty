package cz.cvut.fel.nss.SaunaStudio.kafka;

import cz.cvut.fel.nss.SaunaStudio.dto.ReservationDTO;
import cz.cvut.fel.nss.SaunaStudio.mapper.ReservationMapper;
import cz.cvut.fel.nss.SaunaStudio.service.ReservationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Kafka consumer služba pro zpracování žádostí o rezervace.
 *
 * <p>Tato služba naslouchá Kafka tématům pro zprávy o rezervacích a zpracovává je tím,
 * že převádí data a ukládá je do databáze pomocí {@link ReservationService}.</p>
 */
@Service
public class ReservationConsumer {

    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    /**
     * Konstruktor pro vytvoření {@link ReservationConsumer} se zadaným {@link ReservationService} a {@link ReservationMapper}.
     *
     * @param reservationService Služba pro zpracování operací týkajících se rezervací
     * @param reservationMapper Mapač pro převod {@link ReservationDTO} na {@link Reservation}
     */
    public ReservationConsumer(ReservationService reservationService, ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    /**
     * Zpracovává zprávy o rezervacích přijaté z Kafka tématu.
     *
     * <p>Tato metoda je spuštěna Kafka, když je publikována nová zpráva do tématu "reservation-requests".
     * Převádí {@link ReservationDTO} na entitu {@link Reservation} a ukládá ji do databáze.</p>
     *
     * @param reservation Data o rezervaci přijatá z Kafka
     */
    @KafkaListener(topics = "reservation-requests", groupId = "reservation-group")
    public void processReservation(ReservationDTO reservation) {
        System.out.println("Zpracovávám rezervaci z Kafka: " + reservation);
        reservationService.create(reservationMapper.reservationDtoToReservationBo(reservation));
    }
}
