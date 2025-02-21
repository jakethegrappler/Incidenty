package cz.cvut.fel.nss.SaunaStudio.mapper;

import cz.cvut.fel.nss.SaunaStudio.bo.PriceListBO;
import cz.cvut.fel.nss.SaunaStudio.dto.PriceListDTO;
import org.mapstruct.Mapper;

/**
 * Mapper pro převod mezi entitami {@link PriceList}, byznys objekty {@link PriceListBO} a datovými přenosovými objekty {@link PriceListDTO}.
 *
 * <p>Tento mapper převádí data mezi různými vrstvami aplikace: mezi entitami, byznys objekty a datovými přenosovými objekty.</p>
 */
@Mapper(componentModel = "spring")
public interface PriceListMapper {

    /**
     * Převádí entitu {@link PriceList} na byznys objekt {@link PriceListBO}.
     *
     * @param priceList Entita ceníku, kterou je třeba převést
     * @return Byznys objekt ceníku odpovídající zadané entitě
     */
    PriceListBO priceListToPriceListBO(PriceList priceList);

    /**
     * Převádí byznys objekt {@link PriceListBO} na datový přenosový objekt {@link PriceListDTO}.
     *
     * @param priceListBO Byznys objekt ceníku, který je třeba převést
     * @return Datový přenosový objekt ceníku odpovídající zadanému byznys objektu
     */
    PriceListDTO priceListBoToPriceListDto(PriceListBO priceListBO);

    /**
     * Převádí datový přenosový objekt {@link PriceListDTO} na byznys objekt {@link PriceListBO}.
     *
     * @param priceListDTO Datový přenosový objekt ceníku, který je třeba převést
     * @return Byznys objekt ceníku odpovídající zadanému datovému přenosovému objektu
     */
    PriceListBO priceListDtoToPriceListBo(PriceListDTO priceListDTO);

    /**
     * Převádí byznys objekt {@link PriceListBO} na entitu {@link PriceList}.
     *
     * @param priceListBO Byznys objekt ceníku, který je třeba převést
     * @return Entita ceníku odpovídající zadanému byznys objektu
     */
    PriceList priceListBoToPriceList(PriceListBO priceListBO);

}
