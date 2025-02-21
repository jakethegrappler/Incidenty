package cz.cvut.fel.nss.SaunaStudio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) pro kritéria kalendáře.
 *
 * <p>Tato třída slouží k přenosu informací o kritériích pro filtraci kalendáře
 * mezi vrstvami aplikace, například mezi kontrolery a službami.</p>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarCriteriaDTO {

    /**
     * Datum, podle kterého se mají filtrovat události.
     *
     * <p>Datum je reprezentováno jako {@code String} ve formátu ISO (např. "2024-09-08").</p>
     */
    private String date;

    /**
     * Studio saun, podle kterého se mají filtrovat události.
     */
    private SaunaStudio studio;

}
