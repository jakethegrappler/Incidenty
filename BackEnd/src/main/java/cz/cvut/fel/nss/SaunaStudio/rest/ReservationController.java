package cz.cvut.fel.nss.SaunaStudio.rest;

import cz.cvut.fel.nss.SaunaStudio.bo.ReservationBO;
import cz.cvut.fel.nss.SaunaStudio.dto.HourlyOccupancy;
import cz.cvut.fel.nss.SaunaStudio.mapper.ReservationMapper;
import cz.cvut.fel.nss.SaunaStudio.dto.ReservationDTO;
import cz.cvut.fel.nss.SaunaStudio.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * REST kontroler pro operace spojené s rezervacemi.
 * <p>
 * Tento kontroler poskytuje koncové body pro vytváření nových rezervací, získávání všech rezervací pro zákazníka
 * a získávání informací o obsazenosti studia po hodinách.
 * </p>
 */
@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    /**
     * Konstruktor {@link ReservationController} s uvedenými službami a mapery.
     *
     * @param reservationService Služba pro správu operací spojených s rezervacemi
     * @param reservationMapper Maper pro převod {@link ReservationDTO} na {@link ReservationBO}
     */
    @Autowired
    public ReservationController(ReservationService reservationService, ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    /**
     * Vytváří novou rezervaci.
     *
     * <p>
     * Tento koncový bod vytvoří novou rezervaci na základě poskytnutého {@link ReservationDTO}.
     * Rezervace je vytvořena pomocí {@link ReservationService} a vrácena v odpovědi.
     * </p>
     *
     * @param reservationDTO Data transfer objekt obsahující údaje o rezervaci
     * @return Vytvořená {@link Reservation} entita
     */
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Reservation createReservation(@RequestBody ReservationDTO reservationDTO) {
        Objects.requireNonNull(reservationDTO);

        return reservationService.create(reservationMapper.reservationDtoToReservationBo(reservationDTO));
    }

    /**
     * Získává všechny rezervace pro aktuálně přihlášeného zákazníka.
     *
     * <p>
     * Tento koncový bod vrací seznam všech rezervací spojených s aktuálním zákazníkem.
     * Přístup je omezen pouze na autentizované zákazníky.
     * </p>
     *
     * @return Seznam {@link Reservation} entit spojených s aktuálním zákazníkem
     */
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/customer")
    public List<Reservation> getAllReservationsByCustomer() {
        return reservationService.findByCustomer();
    }

    /**
     * Získává obsazenost studia po hodinách pro konkrétní datum.
     *
     * <p>
     * Tento koncový bod vrací seznam {@link HourlyOccupancy} objektů pro dané studio a datum.
     * Pokud nejsou k dispozici žádná data, vrátí HTTP odpověď bez obsahu.
     * </p>
     *
     * @param date Datum, pro které se má zjistit obsazenost
     * @param studio Název studia
     * @return HTTP odpověď s obsahem {@link List<HourlyOccupancy>} nebo odpověď bez obsahu, pokud nejsou data dostupná
     */
    @GetMapping("/occupancy/{studio}/{date}")
    public ResponseEntity<List<HourlyOccupancy>> getHourlyOccupancy(
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @PathVariable("studio") String studio) {

        List<HourlyOccupancy> hourlyOccupancy = reservationService.findHourlyOccupancyByCriteria(date, studio);

        if (hourlyOccupancy != null && !hourlyOccupancy.isEmpty()) {
            return ResponseEntity.ok(hourlyOccupancy);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
