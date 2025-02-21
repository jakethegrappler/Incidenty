package cz.cvut.fel.nss.SaunaStudio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) pro studio saun.
 *
 * <p>Tato třída slouží k přenosu informací o studiu saun, včetně jeho URL, názvu,
 * kapacity, telefonního čísla, e-mailu a adresy.</p>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaunaStudioDTO {

    /**
     * URL adresa studia saun.
     */
    private String URL;

    /**
     * Název studia saun.
     */
    private String name;

    /**
     * Kapacita studia saun.
     */
    private Integer capacity;

    /**
     * Telefonní číslo studia saun.
     */
    private Integer phoneNumber;

    /**
     * E-mailová adresa studia saun.
     */
    private String email;

    /**
     * Adresa studia saun.
     */
    private String address;

}
