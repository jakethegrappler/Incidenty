package cz.cvut.fel.nss.SaunaStudio.mapper;

import cz.cvut.fel.nss.SaunaStudio.bo.AdminBO;
import cz.cvut.fel.nss.SaunaStudio.dto.AdminDTO;
import cz.cvut.fel.nss.SaunaStudio.model.Admin;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-08T00:12:05+0200",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class AdminMapperImpl implements AdminMapper {

    @Override
    public AdminBO adminToAdminBO(Admin admin) {
        if ( admin == null ) {
            return null;
        }

        AdminBO adminBO = new AdminBO();

        adminBO.setUsername( admin.getEmail() );
        adminBO.setPassword( admin.getPassword() );

        return adminBO;
    }

    @Override
    public AdminDTO adminBoToAdminDto(AdminBO adminBO) {
        if ( adminBO == null ) {
            return null;
        }

        AdminDTO adminDTO = new AdminDTO();

        adminDTO.setUsername( adminBO.getUsername() );
        adminDTO.setPassword( adminBO.getPassword() );

        return adminDTO;
    }

    @Override
    public AdminBO adminDtoToAdminBo(AdminDTO adminDTO) {
        if ( adminDTO == null ) {
            return null;
        }

        AdminBO adminBO = new AdminBO();

        adminBO.setUsername( adminDTO.getUsername() );
        adminBO.setPassword( adminDTO.getPassword() );

        return adminBO;
    }

    @Override
    public Admin adminBoToAdmin(AdminBO adminBO) {
        if ( adminBO == null ) {
            return null;
        }

        Admin admin = new Admin();

        admin.setEmail( adminBO.getUsername() );
        admin.setPassword( adminBO.getPassword() );

        return admin;
    }
}
