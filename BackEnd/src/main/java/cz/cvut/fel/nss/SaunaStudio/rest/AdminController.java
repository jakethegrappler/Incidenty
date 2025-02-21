package cz.cvut.fel.nss.SaunaStudio.rest;

import cz.cvut.fel.nss.SaunaStudio.dto.AdminDTO;
import cz.cvut.fel.nss.SaunaStudio.dto.CustomerDTO;
import cz.cvut.fel.nss.SaunaStudio.dto.PriceListDTO;
import cz.cvut.fel.nss.SaunaStudio.dto.SaunaStudioDTO;
import cz.cvut.fel.nss.SaunaStudio.mapper.AdminMapper;
import cz.cvut.fel.nss.SaunaStudio.mapper.CustomerMapper;
import cz.cvut.fel.nss.SaunaStudio.mapper.PriceListMapper;
import cz.cvut.fel.nss.SaunaStudio.mapper.SaunaStudioMapper;
import cz.cvut.fel.nss.SaunaStudio.model.*;
import cz.cvut.fel.nss.SaunaStudio.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST kontroler pro administrativní operace.
 * <p>
 * Tento kontroler poskytuje koncové body pro správu administrativních úkolů, jako je registrace administrátorů,
 * vytváření cenových seznamů a saunových studií a získávání informací o zákaznících a rezervacích.
 * Přístup k některým koncovým bodům je omezen pouze na uživatele s rolí "ROLE_ADMIN".
 * </p>
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private final AdminService adminService;
    private final PriceListService priceListService;
    private final SaunaStudioService saunaStudioService;
    private final ReservationService reservationService;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final PriceListMapper priceListMapper;
    private final AdminMapper adminMapper;
    private final SaunaStudioMapper saunaStudioMapper;

    /**
     * Konstruktor {@link AdminController} s uvedenými službami a mapery.
     *
     * @param adminService Služba pro správu operací spojených s administrátory
     * @param priceListService Služba pro správu operací spojených s cenovými seznamy
     * @param saunaStudioService Služba pro správu operací spojených se saunovými studii
     * @param reservationService Služba pro správu operací spojených s rezervacemi
     * @param customerService Služba pro správu operací spojených se zákazníky
     * @param customerMapper Maper pro převod {@link CustomerDTO} na {@link Employee}
     * @param priceListMapper Maper pro převod {@link PriceListDTO} na {@link PriceList}
     * @param adminMapper Maper pro převod {@link AdminDTO} na {@link Admin}
     * @param saunaStudioMapper Maper pro převod {@link SaunaStudioDTO} na {@link SaunaStudio}
     */
    @Autowired
    public AdminController(AdminService adminService,
                           PriceListService priceListService,
                           SaunaStudioService saunaStudioService,
                           ReservationService reservationService,
                           CustomerService customerService,
                           CustomerMapper customerMapper,
                           PriceListMapper priceListMapper,
                           AdminMapper adminMapper,
                           SaunaStudioMapper saunaStudioMapper) {
        this.priceListMapper = priceListMapper;
        this.adminService = adminService;
        this.priceListService = priceListService;
        this.saunaStudioService = saunaStudioService;
        this.reservationService = reservationService;
        this.customerService = customerService;
        this.customerMapper = customerMapper;
        this.adminMapper = adminMapper;
        this.saunaStudioMapper = saunaStudioMapper;
    }

    /**
     * Registruje nového administrátora.
     *
     * <p>
     * Tento koncový bod vytvoří nového administrátora na základě poskytnutého {@link AdminDTO}.
     * Administrátor je vytvořen pomocí {@link AdminService} a vrácen v odpovědi.
     * </p>
     *
     * @param adminDTO Data transfer objekt obsahující údaje o registraci administrátora
     * @return Vytvořený {@link Admin} entita
     */
    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Admin AdminRegistration(@RequestBody AdminDTO adminDTO) {
        return adminService.create(adminMapper.adminDtoToAdminBo(adminDTO));
    }

    /**
     * Získává informace o zákazníkovi na základě poskytnutých údajů o zákazníkovi.
     *
     * <p>
     * Tento koncový bod získává informace o zákazníkovi podle telefonního čísla pomocí {@link CustomerService}.
     * Přístup je omezen pouze na uživatele s rolí "ROLE_ADMIN".
     * </p>
     *
     * @param customerDTO Data transfer objekt obsahující údaje o zákazníkovi
     * @return {@link Employee} entita spojená s poskytnutým telefonním číslem
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/info")
    public Employee getCustomerInfo(@RequestBody CustomerDTO customerDTO) {
        return customerService.findByPhoneNumber(customerDTO.getPhoneNumber());
    }

    /**
     * Vytváří nový cenový seznam.
     *
     * <p>
     * Tento koncový bod vytvoří nový cenový seznam na základě poskytnutého {@link PriceListDTO}.
     * Cenový seznam je vytvořen pomocí {@link PriceListService} a vrácen v odpovědi.
     * Přístup je omezen pouze na uživatele s rolí "ROLE_ADMIN".
     * </p>
     *
     * @param priceListDTO Data transfer objekt obsahující údaje o cenovém seznamu
     * @return Vytvořený {@link PriceList} entita
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/createPrice", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PriceList createPriceList(@RequestBody PriceListDTO priceListDTO) {
        return priceListService.create(priceListMapper.priceListDtoToPriceListBo(priceListDTO));
    }

    /**
     * Vytváří nové saunové studio.
     *
     * <p>
     * Tento koncový bod vytvoří nové saunové studio na základě poskytnutého {@link SaunaStudioDTO}.
     * Saunové studio je vytvořeno pomocí {@link SaunaStudioService} a vráceno v odpovědi.
     * Přístup je omezen pouze na uživatele s rolí "ROLE_ADMIN".
     * </p>
     *
     * @param saunaStudioDTO Data transfer objekt obsahující údaje o saunovém studiu
     * @return Vytvořený {@link SaunaStudio} entita
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/createStudio", consumes = MediaType.APPLICATION_JSON_VALUE)
    public SaunaStudio createStudio(@RequestBody SaunaStudioDTO saunaStudioDTO) {
        return saunaStudioService.create(saunaStudioMapper.saunaStudioDtoToSaunaStudioBo(saunaStudioDTO));
    }

    /**
     * Hledá rezervace podle zákazníka.
     *
     * <p>
     * Tento koncový bod získává seznam rezervací spojených se specifikovaným zákazníkem.
     * Přístup je omezen pouze na uživatele s rolí "ROLE_ADMIN".
     * </p>
     *
     * @param customerDTO Data transfer objekt obsahující údaje o zákazníkovi
     * @return Seznam {@link Reservation} entit spojených se zákazníkem
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/findResByCus")
    public List<Reservation> findReservationByCustomer(@RequestBody CustomerDTO customerDTO) {
        return reservationService.findByCustomer(customerMapper.customerDtoToCustomerBo(customerDTO));
    }
}
