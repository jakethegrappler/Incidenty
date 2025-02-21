package cz.cvut.fel.MuzeumSys.mapper;

import cz.cvut.fel.nss.SaunaStudio.bo.AdminBO;
import cz.cvut.fel.nss.SaunaStudio.dto.AdminDTO;
import cz.cvut.fel.nss.SaunaStudio.model.Admin;
import org.mapstruct.Mapper;

/**
 * Mapper pro převod mezi entitami {@link Admin}, {@link AdminBO} a {@link AdminDTO}.
 *
 * <p>Tento mapper převádí data mezi různými vrstvami aplikace: mezi entitami, byznys objekty a datovými přenosovými objekty.</p>
 */
@Mapper(componentModel = "spring")
public interface AdminMapper {

    /**
     * Převádí entitu {@link Admin} na byznys objekt {@link AdminBO}.
     *
     * @param admin Entita administrátora, kterou je třeba převést
     * @return Byznys objekt administrátora odpovídající zadané entitě
     */
    AdminBO adminToAdminBO(Admin admin);

    /**
     * Převádí byznys objekt {@link AdminBO} na datový přenosový objekt {@link AdminDTO}.
     *
     * @param adminBO Byznys objekt administrátora, který je třeba převést
     * @return Datový přenosový objekt administrátora odpovídající zadanému byznys objektu
     */
    AdminDTO adminBoToAdminDto(AdminBO adminBO);

    /**
     * Převádí datový přenosový objekt {@link AdminDTO} na byznys objekt {@link AdminBO}.
     *
     * @param adminDTO Datový přenosový objekt administrátora, který je třeba převést
     * @return Byznys objekt administrátora odpovídající zadanému datovému přenosovému objektu
     */
    AdminBO adminDtoToAdminBo(AdminDTO adminDTO);

    /**
     * Převádí byznys objekt {@link AdminBO} na entitu {@link Admin}.
     *
     * @param adminBO Byznys objekt administrátora, který je třeba převést
     * @return Entita administrátora odpovídající zadanému byznys objektu
     */
    Admin adminBoToAdmin(AdminBO adminBO);
}
