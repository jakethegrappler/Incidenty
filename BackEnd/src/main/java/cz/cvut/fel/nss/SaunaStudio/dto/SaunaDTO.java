package cz.cvut.fel.nss.SaunaStudio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) pro saunu.
 *
 * <p>Tato třída slouží k přenosu informací o sauně, včetně typu sauny, názvu,
 * maximální teploty a URL adresy složky s obrázky sauny.</p>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaunaDTO {

    /**
     * Typ sauny (např. finská, parní).
     */
    private SaunaType saunaType;

    /**
     * Název sauny.
     */
    private String name;

    /**
     * Maximální teplota sauny.
     */
    private Integer maxTemp;

    /**
     * URL adresa složky s obrázky sauny.
     */
    private String imgFolderURL;

}
