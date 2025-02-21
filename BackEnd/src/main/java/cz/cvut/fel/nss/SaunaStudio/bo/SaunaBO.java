package cz.cvut.fel.nss.SaunaStudio.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Business Object (BO) třída, která reprezentuje saunu v systému.
 * Tato třída obsahuje informace o sauně, včetně typu sauny, názvu, maximální teploty a URL složky s obrázky.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaunaBO {

    /**
     * Typ sauny.
     */
    private SaunaType saunaType;

    /**
     * Název sauny.
     */
    private String name;

    /**
     * Maximální teplota, kterou sauna může dosáhnout.
     */
    private Integer maxTemp;

    /**
     * URL složky s obrázky souvisejícími se saunou.
     */
    private String imgFolderURL;
}
