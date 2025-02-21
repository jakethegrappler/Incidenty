package cz.cvut.fel.nss.SaunaStudio.mapper;

import cz.cvut.fel.nss.SaunaStudio.bo.CustomerBO;
import cz.cvut.fel.nss.SaunaStudio.dto.CustomerDTO;
import cz.cvut.fel.nss.SaunaStudio.dto.CustomerRegistrationDTO;
import cz.cvut.fel.nss.SaunaStudio.model.Employee;
import org.mapstruct.Mapper;

/**
 * Mapper pro převod mezi entitami {@link Employee}, byznys objekty {@link CustomerBO} a datovými přenosovými objekty {@link CustomerDTO} a {@link CustomerRegistrationDTO}.
 *
 * <p>Tento mapper převádí data mezi různými vrstvami aplikace: mezi entitami, byznys objekty a datovými přenosovými objekty.</p>
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    /**
     * Převádí entitu {@link Employee} na byznys objekt {@link CustomerBO}.
     *
     * @param employee Entita zákazníka, kterou je třeba převést
     * @return Byznys objekt zákazníka odpovídající zadané entitě
     */
    CustomerBO customerToCustomerBO(Employee employee);

    /**
     * Převádí byznys objekt {@link CustomerBO} na datový přenosový objekt {@link CustomerDTO}.
     *
     * @param customerBO Byznys objekt zákazníka, který je třeba převést
     * @return Datový přenosový objekt zákazníka odpovídající zadanému byznys objektu
     */
    CustomerDTO customerBoToCustomerDto(CustomerBO customerBO);

    /**
     * Převádí datový přenosový objekt {@link CustomerDTO} na byznys objekt {@link CustomerBO}.
     *
     * @param customerDTO Datový přenosový objekt zákazníka, který je třeba převést
     * @return Byznys objekt zákazníka odpovídající zadanému datovému přenosovému objektu
     */
    CustomerBO customerDtoToCustomerBo(CustomerDTO customerDTO);

    /**
     * Převádí byznys objekt {@link CustomerBO} na entitu {@link Employee}.
     *
     * @param customerBO Byznys objekt zákazníka, který je třeba převést
     * @return Entita zákazníka odpovídající zadanému byznys objektu
     */
    Employee customerBoToCustomer(CustomerBO customerBO);

    /**
     * Převádí datový přenosový objekt {@link CustomerRegistrationDTO} na byznys objekt {@link CustomerBO}.
     *
     * @param customerRegistrationDTO Datový přenosový objekt pro registraci zákazníka, který je třeba převést
     * @return Byznys objekt zákazníka odpovídající zadanému datovému přenosovému objektu pro registraci
     */
    CustomerBO customerRegistrationDtoToCustomerBO(CustomerRegistrationDTO customerRegistrationDTO);

}
