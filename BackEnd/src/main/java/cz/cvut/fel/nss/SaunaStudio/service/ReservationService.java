package cz.cvut.fel.nss.SaunaStudio.service;

import cz.cvut.fel.nss.SaunaStudio.bo.CustomerBO;
import cz.cvut.fel.nss.SaunaStudio.bo.ReservationBO;
import cz.cvut.fel.nss.SaunaStudio.dao.EventDao;
import cz.cvut.fel.nss.SaunaStudio.dao.ReservationDao;
import cz.cvut.fel.nss.SaunaStudio.dao.SaunaStudioDao;
import cz.cvut.fel.nss.SaunaStudio.dao.criteria.CalendarCriteria;
import cz.cvut.fel.nss.SaunaStudio.dto.HourlyOccupancy;
import cz.cvut.fel.nss.SaunaStudio.mapper.CustomerMapper;
import cz.cvut.fel.nss.SaunaStudio.mapper.ReservationMapper;
import cz.cvut.fel.nss.SaunaStudio.model.*;
import cz.cvut.fel.nss.SaunaStudio.elasticsearch.ReservationElasticsearchRepository;
import cz.cvut.fel.nss.SaunaStudio.security.SecurityUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Služba pro správu rezervací.
 * <p>
 * Třída poskytuje metody pro vytváření, vyhledávání, aktualizaci a mazání rezervací. Integruje také Elasticsearch pro vyhledávání rezervací.
 * </p>
 */
@Service
public class ReservationService {

    private final ReservationElasticsearchRepository reservationElasticsearchRepository;
    private final ReservationMapper reservationMapper;
    private final ReservationDao reservationDao;
    private final EventDao eventDao;
    private final CustomerMapper customerMapper;
    private final SaunaStudioDao saunaStudioDao;

    /**
     * Vytváří instanci {@link ReservationService}.
     *
     * @param reservationElasticsearchRepository Elasticsearch repozitář pro rezervace
     * @param reservationMapper Mapa pro převod mezi {@link ReservationBO} a {@link Reservation}
     * @param reservationDao DAO pro správu rezervací
     * @param eventDao DAO pro správu událostí
     * @param customerMapper Mapa pro převod mezi {@link CustomerBO} a {@link Employee}
     * @param saunaStudioDao DAO pro správu saunových studií
     */
    @Autowired
    public ReservationService(
            ReservationElasticsearchRepository reservationElasticsearchRepository,
            ReservationMapper reservationMapper,
            ReservationDao reservationDao, EventDao eventDao, CustomerMapper customerMapper, SaunaStudioDao saunaStudioDao) {
        this.reservationElasticsearchRepository = reservationElasticsearchRepository;
        this.reservationMapper = reservationMapper;
        this.reservationDao = reservationDao;
        this.eventDao = eventDao;
        this.customerMapper = customerMapper;
        this.saunaStudioDao = saunaStudioDao;
    }

    /**
     * Vytvoří novou rezervaci.
     * <p>
     * Tato metoda ověří platnost rezervace, nastaví zákazníka a uloží rezervaci do databáze a Elasticsearch.
     * </p>
     *
     * @param reservationBO Business objekt obsahující informace o rezervaci
     * @return Vytvořená {@link Reservation} rezervace
     * @throws NullPointerException Pokud je {@code reservationBO} nebo zákazník {@code null}
     */
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @Transactional
    public Reservation create(ReservationBO reservationBO) {
        Employee employee = Objects.requireNonNull(SecurityUtils.getCurrentUserDetails()).getEmployee();
        Objects.requireNonNull(reservationBO, "Reservation must not be null");
        Objects.requireNonNull(employee, "Customer must not be null");

        reservationBO.validate(eventDao);
        reservationBO.setEmployee(employee);

        Reservation reservation = reservationMapper.reservationBoToReservation(reservationBO);
        reservationDao.persist(reservation);
        reservationElasticsearchRepository.save(reservation);

        return reservation;
    }

    /**
     * Najde všechny rezervace aktuálního zákazníka.
     * <p>
     * Používá Elasticsearch repozitář pro získání rezervací.
     * </p>
     *
     * @return List rezervací aktuálního zákazníka
     * @throws NullPointerException Pokud je zákazník {@code null}
     */
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    @Transactional
    public List<Reservation> findByCustomer() {
        Employee employee = Objects.requireNonNull(SecurityUtils.getCurrentUserDetails()).getEmployee();
        Objects.requireNonNull(employee, "Customer must not be null");

        return reservationElasticsearchRepository.findByCustomer(employee); // Use Elasticsearch repository
    }

    /**
     * Najde všechny rezervace zákazníka podle zadaného {@link CustomerBO}.
     * <p>
     * Používá Elasticsearch repozitář pro získání rezervací.
     * </p>
     *
     * @param customerBO Business objekt obsahující informace o zákazníkovi
     * @return List rezervací zákazníka
     * @throws NullPointerException Pokud je {@code customerBO} nebo jeho zákazník {@code null}
     */
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    @Transactional
    public List<Reservation> findByCustomer(CustomerBO customerBO) {
        Employee employee = customerMapper.customerBoToCustomer(customerBO);
        Objects.requireNonNull(employee, "Customer must not be null");

        return reservationElasticsearchRepository.findByCustomer(employee); // Use Elasticsearch repository
    }

    /**
     * Získá hodinovou obsazenost podle zadaných kritérií.
     * <p>
     * Tato metoda vrací hodinovou obsazenost pro specifikované studio na daný den.
     * </p>
     *
     * @param date Datum pro hledání obsazenosti
     * @param studioName Název saunového studia
     * @return List {@link HourlyOccupancy} pro daný den a studio
     * @throws IllegalArgumentException Pokud studio není nalezeno
     */
    @Transactional
    public List<HourlyOccupancy> findHourlyOccupancyByCriteria(LocalDate date, String studioName) {
        SaunaStudio studio = saunaStudioDao.findByName(studioName);
        if (studio == null) {
            throw new IllegalArgumentException("Studio not found");
        }
        CalendarCriteria criteria = new CalendarCriteria(date, studio);
        return reservationDao.findHourlyOccupancyByCriteria(criteria);
    }

    /**
     * Najde rezervace podle data a ID studia.
     * <p>
     * Používá Elasticsearch repozitář pro získání rezervací.
     * </p>
     *
     * @param startTime Počáteční čas rezervace
     * @param studioID ID saunového studia
     * @return List rezervací pro dané datum a studio
     */
    public List<Reservation> findReservationsByDateAndStudio(LocalDateTime startTime, Integer studioID) {
        return reservationElasticsearchRepository.findByStartTimeAndStudioId(startTime, studioID);
    }

    /**
     * Najde rezervace podle rozsahu dat.
     * <p>
     * Používá Elasticsearch repozitář pro získání rezervací.
     * </p>
     *
     * @param start Počáteční časový bod
     * @param end Konečný časový bod
     * @return List rezervací mezi zadanými časy
     */
    @Transactional
    public List<Reservation> findByDateRange(LocalDateTime start, LocalDateTime end) {
        return reservationElasticsearchRepository.findByStartTimeBetween(start, end);
    }

    /**
     * Odstraní rezervaci.
     * <p>
     * Tato metoda odstraní rezervaci z databáze a z Elasticsearch.
     * </p>
     *
     * @param reservationBO Business objekt obsahující informace o rezervaci
     * @throws NullPointerException Pokud je {@code reservationBO} {@code null}
     */
    @Transactional
    public void deleteReservation(ReservationBO reservationBO) {
        Objects.requireNonNull(reservationBO, "Reservation must not be null");
        reservationDao.remove(reservationMapper.reservationBoToReservation(reservationBO));
        reservationElasticsearchRepository.delete(reservationMapper.reservationBoToReservation(reservationBO)); // Delete from Elasticsearch
    }

    /**
     * Aktualizuje rezervaci.
     * <p>
     * Tato metoda aktualizuje rezervaci v databázi a Elasticsearch.
     * </p>
     *
     * @param reservationBO Business objekt obsahující informace o rezervaci
     * @return Aktualizovaná {@link Reservation} rezervace
     * @throws NullPointerException Pokud je {@code reservationBO} {@code null}
     */
    @Transactional
    public Reservation updateReservation(ReservationBO reservationBO) {
        Objects.requireNonNull(reservationBO, "Reservation must not be null");
        Reservation updatedReservation = reservationDao.update(reservationMapper.reservationBoToReservation(reservationBO));
        reservationElasticsearchRepository.save(updatedReservation); // Update in Elasticsearch
        return updatedReservation;
    }
}
