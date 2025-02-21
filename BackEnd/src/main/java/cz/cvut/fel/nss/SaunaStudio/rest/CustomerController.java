package cz.cvut.fel.nss.SaunaStudio.rest;

import cz.cvut.fel.nss.SaunaStudio.bo.CustomerBO;
import cz.cvut.fel.nss.SaunaStudio.dto.CustomerRegistrationDTO;
import cz.cvut.fel.nss.SaunaStudio.mapper.CustomerMapper;
import cz.cvut.fel.nss.SaunaStudio.model.Employee;
import cz.cvut.fel.nss.SaunaStudio.security.SecurityUtils;
import cz.cvut.fel.nss.SaunaStudio.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * REST kontroler pro operace spojené se zákazníky.
 * <p>
 * Tento kontroler poskytuje koncové body pro registraci nových zákazníků, získávání informací o aktuálním zákazníkovi
 * a odhlášení zákazníka. Některé operace jsou dostupné pouze pro autentizované zákazníky.
 * </p>
 */
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    /**
     * Konstruktor {@link CustomerController} s uvedenými službami a mapery.
     *
     * @param customerService Služba pro správu operací spojených se zákazníky
     * @param customerMapper Maper pro převod {@link CustomerRegistrationDTO} na {@link CustomerBO}
     */
    @Autowired
    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    /**
     * Registruje nového zákazníka.
     *
     * <p>
     * Tento koncový bod vytvoří nového zákazníka na základě poskytnutého {@link CustomerRegistrationDTO}.
     * Zákazník je vytvořen pomocí {@link CustomerService} a vrácen v odpovědi.
     * </p>
     *
     * @param customerRegistrationDTO Data transfer objekt obsahující údaje o registraci zákazníka
     * @return Vytvořený {@link Employee} entita
     */
    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employee customerRegistration(@RequestBody CustomerRegistrationDTO customerRegistrationDTO) {
        return customerService.create(customerMapper.customerRegistrationDtoToCustomerBO(customerRegistrationDTO));
    }

    /**
     * Získává informace o aktuálním zákazníkovi.
     *
     * <p>
     * Tento koncový bod vrací informace o aktuálně přihlášeném zákazníkovi.
     * Přístup je omezen pouze na autentizované zákazníky.
     * </p>
     *
     * @return {@link Employee} entita spojená s aktuálním uživatelským jménem
     */
    @CachePut(value = "userCache", key = "#root.target.getCurrentUsername()")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping(value = "/info")
    public Employee getCustomerInfo() {
        return Objects.requireNonNull(SecurityUtils.getCurrentUserDetails()).getEmployee();
    }

    /**
     * Odhlašuje aktuálního zákazníka.
     *
     * <p>
     * Tento koncový bod provede odhlášení zákazníka a vyprázdní cache související s tímto uživatelským jménem.
     * </p>
     */
    @CacheEvict(value = "userCache", key = "#root.target.getCurrentUsername()")
    @PostMapping(value = "/logout")
    public void logout() {
        // Logika pro odhlášení může být implementována zde
    }
}
