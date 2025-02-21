package cz.cvut.fel.nss.SaunaStudio.dto;

import cz.cvut.fel.nss.SaunaStudio.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) pro rezervaci.
 *
 * <p>Tato třída slouží k přenosu informací o rezervacích událostí v saunovém studiu,
 * včetně detailů události, času začátku a konce rezervace, počtu osob, stavu rezervace,
 * saunového studia a zákazníka.</p>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    /**
     * Událost, která je rezervována.
     */
    private Event event;

    /**
     * Čas konce rezervace ve formátu String.
     */
    private String endTime;

    /**
     * Počet osob pro rezervaci.
     */
    private int numOfPeople;

    /**
     * Čas začátku rezervace ve formátu String.
     */
    private String startTime;

    /**
     * Stav rezervace (např. potvrzená, zrušená).
     */
    private String stateType;

    /**
     * Saunové studio, kde se rezervace uskuteční.
     */
    private SaunaStudio saunaStudio;

    /**
     * Zákazník, který provedl rezervaci.
     */
    private Employee employee;

}
