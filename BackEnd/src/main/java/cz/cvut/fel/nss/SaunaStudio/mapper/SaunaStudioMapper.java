package cz.cvut.fel.nss.SaunaStudio.mapper;

import cz.cvut.fel.nss.SaunaStudio.bo.SaunaStudioBO;
import cz.cvut.fel.nss.SaunaStudio.dto.SaunaStudioDTO;
import org.mapstruct.Mapper;

/**
 * Mapper pro převod mezi entitami {@link SaunaStudio}, byznys objekty {@link SaunaStudioBO} a datovými přenosovými objekty {@link SaunaStudioDTO}.
 *
 * <p>Tento mapper převádí data mezi různými vrstvami aplikace: mezi entitami, byznys objekty a datovými přenosovými objekty.</p>
 */
@Mapper(componentModel = "spring")
public interface SaunaStudioMapper {

    /**
     * Převádí entitu {@link SaunaStudio} na byznys objekt {@link SaunaStudioBO}.
     *
     * @param saunaStudio Entita saunového studia, kterou je třeba převést
     * @return Byznys objekt saunového studia odpovídající zadané entitě
     */
    SaunaStudioBO saunaStudioToSaunaStudioBO(SaunaStudio saunaStudio);

    /**
     * Převádí byznys objekt {@link SaunaStudioBO} na datový přenosový objekt {@link SaunaStudioDTO}.
     *
     * @param saunaStudioBO Byznys objekt saunového studia, který je třeba převést
     * @return Datový přenosový objekt saunového studia odpovídající zadanému byznys objektu
     */
    SaunaStudioDTO saunaStudioBoToSaunaStudioDto(SaunaStudioBO saunaStudioBO);

    /**
     * Převádí datový přenosový objekt {@link SaunaStudioDTO} na byznys objekt {@link SaunaStudioBO}.
     *
     * @param saunaStudioDTO Datový přenosový objekt saunového studia, který je třeba převést
     * @return Byznys objekt saunového studia odpovídající zadanému datovému přenosovému objektu
     */
    SaunaStudioBO saunaStudioDtoToSaunaStudioBo(SaunaStudioDTO saunaStudioDTO);

    /**
     * Převádí byznys objekt {@link SaunaStudioBO} na entitu {@link SaunaStudio}.
     *
     * @param saunaStudioBO Byznys objekt saunového studia, který je třeba převést
     * @return Entita saunového studia odpovídající zadanému byznys objektu
     */
    SaunaStudio saunaStudioBoToSaunaStudio(SaunaStudioBO saunaStudioBO);
}
