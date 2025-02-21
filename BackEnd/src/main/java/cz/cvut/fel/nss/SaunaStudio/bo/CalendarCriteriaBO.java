package cz.cvut.fel.nss.SaunaStudio.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Business Object (BO) třída, která reprezentuje kritéria pro kalendářní vyhledávání.
 * Tato třída se používá pro filtrování výsledků podle data a konkrétního sauna studia.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarCriteriaBO {

    /**
     * Datum, podle kterého se mají filtrovat výsledky v kalendáři.
     */
    private String date;

    /**
     * Instance SaunaStudio, která specifikuje, v jakém sauna studiu má být vyhledáváno.
     */
    private SaunaStudio studio;

}
