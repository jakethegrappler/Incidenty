package cz.cvut.fel.nss.SaunaStudio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) pro registraci zákazníka.
 *
 * <p>Tato třída slouží k přenosu informací potřebných pro registraci nového zákazníka
 * mezi vrstvami aplikace, například mezi kontrolery a službami.</p>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegistrationDTO {

    /**
     * Uživatelské jméno zákazníka.
     */
    private String username;

    /**
     * Heslo zákazníka.
     */
    private String password;

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

}
