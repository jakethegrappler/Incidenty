package cz.cvut.fel.nss.SaunaStudio.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Business Object (BO) třída, která reprezentuje událost v sauna studiu.
 * Tato třída obsahuje informace o události, jako jsou název, popis, časový harmonogram,
 * kapacita a další vlastnosti, které jsou relevantní pro správu a zobrazení událostí.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventBO {

    /**
     * Název události.
     */
    private String name;

    /**
     * Popis události.
     */
    private String description;

    /**
     * Počáteční čas události (např. ve formátu HH:mm).
     */
    private String startTime;

    /**
     * Konečný čas události (např. ve formátu HH:mm).
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
     * Dostupnost události (true, pokud je dostupná pro rezervace).
     */
    private Boolean isAvailable;

    /**
     * URL složky s obrázky souvisejícími s událostí.
     */
    private String imgFolderURL;

    /**
     * Seznam událostí typu SaunaEvent, které jsou součástí této události.
     */
    private List<SaunaEvent> saunaEvents;
}
