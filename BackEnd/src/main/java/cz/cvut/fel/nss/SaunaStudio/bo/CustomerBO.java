package cz.cvut.fel.nss.SaunaStudio.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Business Object (BO) třída, která reprezentuje zákazníka v systému.
 * Tato třída obsahuje informace o zákazníkovi, které mohou být využity
 * pro různé obchodní operace v rámci aplikace.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBO {

    /**
     * Uživatelské jméno zákazníka.
     */
    private String username;

    /**
     * Heslo zákazníka.
     */
    private String password;

    /**
     * Křestní jméno zákazníka.
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
