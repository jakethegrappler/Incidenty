package cz.cvut.fel.nss.SaunaStudio.mapper;

import cz.cvut.fel.nss.SaunaStudio.bo.SaunaBO;
import cz.cvut.fel.nss.SaunaStudio.dto.SaunaDTO;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-08T00:12:05+0200",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class SaunaMapperImpl implements SaunaMapper {

    @Override
    public SaunaBO saunaToSaunaBO(Sauna sauna) {
        if ( sauna == null ) {
            return null;
        }

        SaunaBO saunaBO = new SaunaBO();

        saunaBO.setSaunaType( sauna.getSaunaType() );
        saunaBO.setName( sauna.getName() );
        saunaBO.setMaxTemp( sauna.getMaxTemp() );
        saunaBO.setImgFolderURL( sauna.getImgFolderURL() );

        return saunaBO;
    }

    @Override
    public SaunaDTO saunaBoToSaunaDto(SaunaBO saunaBO) {
        if ( saunaBO == null ) {
            return null;
        }

        SaunaDTO saunaDTO = new SaunaDTO();

        saunaDTO.setSaunaType( saunaBO.getSaunaType() );
        saunaDTO.setName( saunaBO.getName() );
        saunaDTO.setMaxTemp( saunaBO.getMaxTemp() );
        saunaDTO.setImgFolderURL( saunaBO.getImgFolderURL() );

        return saunaDTO;
    }

    @Override
    public SaunaBO saunaDtoToSaunaBo(SaunaDTO saunaDTO) {
        if ( saunaDTO == null ) {
            return null;
        }

        SaunaBO saunaBO = new SaunaBO();

        saunaBO.setSaunaType( saunaDTO.getSaunaType() );
        saunaBO.setName( saunaDTO.getName() );
        saunaBO.setMaxTemp( saunaDTO.getMaxTemp() );
        saunaBO.setImgFolderURL( saunaDTO.getImgFolderURL() );

        return saunaBO;
    }

    @Override
    public Sauna saunaBoToSauna(SaunaBO saunaBO) {
        if ( saunaBO == null ) {
            return null;
        }

        Sauna sauna = new Sauna();

        sauna.setSaunaType( saunaBO.getSaunaType() );
        sauna.setName( saunaBO.getName() );
        sauna.setMaxTemp( saunaBO.getMaxTemp() );
        sauna.setImgFolderURL( saunaBO.getImgFolderURL() );

        return sauna;
    }
}
