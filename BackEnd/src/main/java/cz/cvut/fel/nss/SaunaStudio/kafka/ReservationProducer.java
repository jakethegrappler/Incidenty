package cz.cvut.fel.nss.SaunaStudio.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Kafka producent pro odesílání žádostí o rezervaci.
 *
 * <p>Tato služba je zodpovědná za odesílání objektů {@link Reservation} do Kafka tématu.</p>
 */
@Service
public class ReservationProducer {

    private final KafkaTemplate<String, Reservation> kafkaTemplate;

    /**
     * Konstruktor {@link ReservationProducer} s daným {@link KafkaTemplate}.
     *
     * @param kafkaTemplate Kafka šablona pro odesílání zpráv
     */
    public ReservationProducer(KafkaTemplate<String, Reservation> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Odesílá žádost o rezervaci do Kafka tématu "reservation-requests".
     *
     * <p>Tato metoda publikuje zadanou {@link Reservation} do Kafka tématu pro další zpracování.</p>
     *
     * @param reservation Rezervace, která má být odeslána do Kafka
     */
    public void sendReservationRequest(Reservation reservation) {
        kafkaTemplate.send("reservation-requests", reservation);
        System.out.println("Žádost o rezervaci odeslána do Kafka: " + reservation);
    }
}
