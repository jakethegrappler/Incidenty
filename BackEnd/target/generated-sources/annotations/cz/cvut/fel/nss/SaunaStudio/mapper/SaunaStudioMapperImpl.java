package cz.cvut.fel.nss.SaunaStudio.mapper;

import cz.cvut.fel.nss.SaunaStudio.bo.SaunaStudioBO;
import cz.cvut.fel.nss.SaunaStudio.dto.SaunaStudioDTO;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-08T00:12:05+0200",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class SaunaStudioMapperImpl implements SaunaStudioMapper {


    @Override
    public SaunaStudioBO saunaStudioToSaunaStudioBO(SaunaStudio saunaStudio) {
        if ( saunaStudio == null ) {
            return null;
        }

        SaunaStudioBO saunaStudioBO = new SaunaStudioBO();

        return saunaStudioBO;
    }

    @Override
    public SaunaStudioDTO saunaStudioBoToSaunaStudioDto(SaunaStudioBO saunaStudioBO) {
        if ( saunaStudioBO == null ) {
            return null;
        }

        SaunaStudioDTO saunaStudioDTO = new SaunaStudioDTO();

        saunaStudioDTO.setURL( saunaStudioBO.getURL() );
        saunaStudioDTO.setName( saunaStudioBO.getName() );
        saunaStudioDTO.setCapacity( saunaStudioBO.getCapacity() );
        saunaStudioDTO.setPhoneNumber( saunaStudioBO.getPhoneNumber() );
        saunaStudioDTO.setEmail( saunaStudioBO.getEmail() );
        saunaStudioDTO.setAddress( saunaStudioBO.getAddress() );

        return saunaStudioDTO;
    }

    @Override
    public SaunaStudioBO saunaStudioDtoToSaunaStudioBo(SaunaStudioDTO saunaStudioDTO) {
        if ( saunaStudioDTO == null ) {
            return null;
        }

        SaunaStudioBO saunaStudioBO = new SaunaStudioBO();

        saunaStudioBO.setURL( saunaStudioDTO.getURL() );
        saunaStudioBO.setName( saunaStudioDTO.getName() );
        saunaStudioBO.setCapacity( saunaStudioDTO.getCapacity() );
        saunaStudioBO.setPhoneNumber( saunaStudioDTO.getPhoneNumber() );
        saunaStudioBO.setEmail( saunaStudioDTO.getEmail() );
        saunaStudioBO.setAddress( saunaStudioDTO.getAddress() );

        return saunaStudioBO;
    }

    @Override
    public SaunaStudio saunaStudioBoToSaunaStudio(SaunaStudioBO saunaStudioBO) {
        if ( saunaStudioBO == null ) {
            return null;
        }

        SaunaStudio saunaStudio = new SaunaStudio();

        saunaStudio.setURL( saunaStudioBO.getURL() );
        saunaStudio.setName( saunaStudioBO.getName() );
        saunaStudio.setCapacity( saunaStudioBO.getCapacity() );
        saunaStudio.setPhoneNumber( saunaStudioBO.getPhoneNumber() );
        saunaStudio.setEmail( saunaStudioBO.getEmail() );
        saunaStudio.setAddress( saunaStudioBO.getAddress() );

        return saunaStudio;
    }
}
