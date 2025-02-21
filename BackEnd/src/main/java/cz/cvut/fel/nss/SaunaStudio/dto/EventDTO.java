package cz.cvut.fel.nss.SaunaStudio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Data Transfer Object (DTO) pro událost.
 *
 * <p>Tato třída slouží k přenosu informací o události mezi vrstvami aplikace,
 * například mezi kontrolery a službami, nebo při komunikaci s frontendem.</p>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    /**
     * Název události.
     */
    private String name;

    /**
     * Popis události.
     */
    private String description;

    /**
     * Čas začátku události ve formátu ISO.
     */
    private String startTime;

    /**
     * Čas konce události ve formátu ISO.
     */
    private String endTime;

    /**
     * Celková kapacita události.
     */
    private Integer totalCapacity;

    /**
     * Volná kapacita události.
     */
    private Integer freeCapacity;

    /**
     * Indikátor, zda je událost dostupná.
     */
    private Boolean isAvailable;

    /**
     * URL složky s obrázky události.
     */
    private String imgFolderURL;

    /**
     * Seznam událostí sauny spojených s touto událostí.
     */
    private List<SaunaEvent> saunaEvents;

}
