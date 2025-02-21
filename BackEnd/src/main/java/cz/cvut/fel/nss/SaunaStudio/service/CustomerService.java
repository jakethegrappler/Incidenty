package cz.cvut.fel.nss.SaunaStudio.service;

import cz.cvut.fel.nss.SaunaStudio.bo.CustomerBO;
import cz.cvut.fel.nss.SaunaStudio.dao.CustomerDao;
import cz.cvut.fel.nss.SaunaStudio.exception.MyException;
import cz.cvut.fel.nss.SaunaStudio.mapper.CustomerMapper;
import cz.cvut.fel.nss.SaunaStudio.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Objects;

/**
 * Služba pro správu zákazníků.
 * <p>
 * Třída poskytuje metody pro vytváření zákazníků, vyhledávání zákazníků podle uživatelského jména, telefonního čísla
 * a emailu, stejně jako pro kontrolu existence zákazníka a získání seznamu všech zákazníků.
 * </p>
 */
@Service
public class CustomerService {

    private final PasswordEncoder passwordEncoder;
    private final CustomerDao customerDao;
    private final CustomerMapper customerMapper;

    /**
     * Vytváří instanci {@link CustomerService}.
     *
     * @param passwordEncoder Kódovač hesel pro šifrování hesel zákazníků
     * @param customerDao DAO pro správu zákazníků
     * @param customerMapper Mapa pro převod mezi {@link CustomerBO} a {@link Employee}
     */
    @Autowired
    public CustomerService(PasswordEncoder passwordEncoder, CustomerDao customerDao, CustomerMapper customerMapper) {
        this.passwordEncoder = passwordEncoder;
        this.customerDao = customerDao;
        this.customerMapper = customerMapper;
    }

    /**
     * Vytvoří nového zákazníka.
     * <p>
     * Tato metoda zkontroluje, zda uživatelské jméno nebo email již nejsou použity. Pokud ano, vyvolá {@link MyException}.
     * Zákazník je následně uložen do databáze.
     * </p>
     *
     * @param customerBO Business objekt obsahující informace o zákazníkovi
     * @return Vytvořený {@link Employee}
     * @throws MyException Pokud uživatelské jméno nebo email již existují
     */
    @Transactional
    public Employee create(CustomerBO customerBO) {
        Objects.requireNonNull(customerBO);
        if (customerDao.findByUsername(customerBO.getUsername()) != null)
            throw new MyException("Username is already taken.");
        if (customerDao.findByEmail(customerBO.getEmail()) != null)
            throw new MyException("Email is already used by different account.");

        Employee employee = customerMapper.customerBoToCustomer(customerBO);
        customerDao.persist(employee);
        return employee;
    }

    /**
     * Vyhledá zákazníka podle jeho uživatelského jména.
     * <p>
     * Výsledky jsou cachovány pro zvýšení výkonu.
     * </p>
     *
     * @param username Uživatelské jméno zákazníka
     * @return {@link Employee} zákazník s daným uživatelským jménem
     */
    @Cacheable(value = "userCache", key = "#username")
    @Transactional
    public Employee findByUsername(String username) {
        return customerDao.findByUsername(username);
    }

    /**
     * Vyhledá zákazníka podle jeho telefonního čísla.
     * <p>
     * Výsledky jsou cachovány pro zvýšení výkonu.
     * </p>
     *
     * @param phoneNumber Telefonní číslo zákazníka
     * @return {@link Employee} zákazník s daným telefonním číslem
     */
    @Cacheable(value = "userCache", key = "#phoneNumber")
    @Transactional
    public Employee findByPhoneNumber(String phoneNumber) {
        return customerDao.findByPhoneNumber(phoneNumber);
    }

    /**
     * Vyhledá zákazníka podle jeho emailu.
     * <p>
     * Výsledky jsou cachovány pro zvýšení výkonu.
     * </p>
     *
     * @param email Email zákazníka
     * @return {@link Employee} zákazník s daným emailem
     */
    @Cacheable(value = "userCache", key = "#email")
    @Transactional
    public Employee findByEmail(String email) {
        return customerDao.findByEmail(email);
    }

    /**
     * Zkontroluje, zda již existuje zákazník s daným emailem.
     *
     * @param email Email zákazníka
     * @return True, pokud zákazník s daným emailem existuje, jinak False
     */
    @Transactional(readOnly = true)
    public boolean exists(String email) {
        return customerDao.findByEmail(email) != null;
    }

    /**
     * Získá seznam všech zákazníků.
     *
     * @return List všech {@link Employee} zákazníků
     */
    @Transactional
    public List<Employee> getCustomers() {
        return customerDao.findAll();
    }
}
