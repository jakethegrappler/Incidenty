package cz.cvut.fel.nss.SaunaStudio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) pro administrátora.
 * Tato třída slouží k přenosu údajů o administrátorovi mezi vrstvami aplikace.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {

    /**
     * Uživatelské jméno administrátora.
     */
    private String username;

    /**
     * Heslo administrátora.
     */
    private String password;

}
