package cz.cvut.fel.nss.SaunaStudio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) pro zákazníka.
 *
 * <p>Tato třída slouží k přenosu informací o zákazníkovi mezi vrstvami aplikace,
 * například mezi kontrolery a službami.</p>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    /**
     * Jméno zákazníka.
     */
    private String firstName;

    /**
     * Příjmení zákazníka.
     */
    private String lastName;

    /**
     * E-mailová adresa zákazníka.
     */
    private String email;

    /**
     * Telefonní číslo zákazníka.
     */
    private String phoneNumber;

    /**
     * Indikátor, zda je zákazník pozastaven.
     *
     * <p>{@code true} znamená, že zákazník je pozastaven, {@code false} znamená, že není.</p>
     */
    private Boolean suspended;

}
