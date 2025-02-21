package cz.cvut.fel.nss.SaunaStudio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) pro cenový seznam.
 *
 * <p>Tato třída slouží k přenosu informací o cenách za služby v saunovém studiu,
 * včetně ceny, typu trvání a příslušného saunového studia.</p>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceListDTO {

    /**
     * Cena za službu nebo rezervaci.
     */
    private BigDecimal price;

    /**
     * Typ trvání služby (např. hodinový, denní).
     */
    private DurationType durationType;

    /**
     * Saunové studio, ke kterému se cena vztahuje.
     */
    private SaunaStudio saunaStudio;

}
