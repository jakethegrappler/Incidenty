package cz.cvut.fel.nss.SaunaStudio.mapper;

import cz.cvut.fel.nss.SaunaStudio.bo.SaunaBO;
import cz.cvut.fel.nss.SaunaStudio.dto.SaunaDTO;
import org.mapstruct.Mapper;

/**
 * Mapper pro převod mezi entitami {@link Sauna}, byznys objekty {@link SaunaBO} a datovými přenosovými objekty {@link SaunaDTO}.
 *
 * <p>Tento mapper převádí data mezi různými vrstvami aplikace: mezi entitami, byznys objekty a datovými přenosovými objekty.</p>
 */
@Mapper(componentModel = "spring")
public interface SaunaMapper {

    /**
     * Převádí entitu {@link Sauna} na byznys objekt {@link SaunaBO}.
     *
     * @param sauna Entita sauny, kterou je třeba převést
     * @return Byznys objekt sauny odpovídající zadané entitě
     */
    SaunaBO saunaToSaunaBO(Sauna sauna);

    /**
     * Převádí byznys objekt {@link SaunaBO} na datový přenosový objekt {@link SaunaDTO}.
     *
     * @param saunaBO Byznys objekt sauny, který je třeba převést
     * @return Datový přenosový objekt sauny odpovídající zadanému byznys objektu
     */
    SaunaDTO saunaBoToSaunaDto(SaunaBO saunaBO);

    /**
     * Převádí datový přenosový objekt {@link SaunaDTO} na byznys objekt {@link SaunaBO}.
     *
     * @param saunaDTO Datový přenosový objekt sauny, který je třeba převést
     * @return Byznys objekt sauny odpovídající zadanému datovému přenosovému objektu
     */
    SaunaBO saunaDtoToSaunaBo(SaunaDTO saunaDTO);

    /**
     * Převádí byznys objekt {@link SaunaBO} na entitu {@link Sauna}.
     *
     * @param saunaBO Byznys objekt sauny, který je třeba převést
     * @return Entita sauny odpovídající zadanému byznys objektu
     */
    Sauna saunaBoToSauna(SaunaBO saunaBO);
}
