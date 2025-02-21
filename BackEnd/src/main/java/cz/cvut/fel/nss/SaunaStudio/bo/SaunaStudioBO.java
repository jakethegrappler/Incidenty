package cz.cvut.fel.nss.SaunaStudio.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Business Object (BO) třída, která reprezentuje sauna studio v systému.
 * Tato třída obsahuje informace o sauna studiu, včetně URL, názvu, kapacity,
 * telefonního čísla, e-mailu a adresy.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaunaStudioBO {

    /**
     * URL webové stránky sauna studia.
     */
    private String URL;

    /**
     * Název sauna studia.
     */
    private String name;

    /**
     * Kapacita sauna studia.
     */
    private Integer capacity;

    /**
     * Telefonní číslo sauna studia.
     */
    private Integer phoneNumber;

    /**
     * E-mailová adresa sauna studia.
     */
    private String email;

    /**
     * Adresa sauna studia.
     */
    private String address;
}
