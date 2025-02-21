package cvut.fel.nss.SaunaStudio.mapperTest;

import cz.cvut.fel.nss.SaunaStudio.bo.CustomerBO;
import cz.cvut.fel.nss.SaunaStudio.configuration.TestConfig;
import cz.cvut.fel.nss.SaunaStudio.mapper.CustomerMapper;
import cz.cvut.fel.nss.SaunaStudio.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class EmployeeMapperTest {

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void testCustomerToCustomerBo() {
        Employee employee = new Employee();
        employee.setEmail("test@test.test");
        employee.setSuspended(false);
        employee.setFirstName("firstname");
        employee.setLastName("lastname");
        employee.setPhoneNumber("123123123");
        employee.setId(1);
        employee.setPassword("password");
        employee.setEmail("username");

        CustomerBO customerBO = customerMapper.customerToCustomerBO(employee);

        assertEquals(employee.getPhoneNumber(), customerBO.getPhoneNumber());
        assertEquals(employee.getEmail(), customerBO.getEmail());
        assertEquals(employee.getFirstName(), customerBO.getFirstName());
        assertEquals(employee.getLastName(), customerBO.getLastName());
    }
}