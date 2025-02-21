package cz.cvut.fel.nss.SaunaStudio.configuration;

import cz.cvut.fel.nss.SaunaStudio.mapper.CustomerMapper;
import cz.cvut.fel.nss.SaunaStudio.mapper.ReservationMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public ReservationMapper reservationMapper() {
        return Mappers.getMapper(ReservationMapper.class);
    }

    @Bean
    public CustomerMapper customerMapper() {
        return Mappers.getMapper(CustomerMapper.class);
    }
}
