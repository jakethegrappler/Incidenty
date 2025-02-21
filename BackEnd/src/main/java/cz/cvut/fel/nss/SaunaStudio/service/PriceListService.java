package cz.cvut.fel.nss.SaunaStudio.service;

import cz.cvut.fel.nss.SaunaStudio.bo.PriceListBO;
import cz.cvut.fel.nss.SaunaStudio.bo.SaunaStudioBO;
import cz.cvut.fel.nss.SaunaStudio.dao.PriceListDao;
import cz.cvut.fel.nss.SaunaStudio.exception.MyException;
import cz.cvut.fel.nss.SaunaStudio.mapper.PriceListMapper;
import cz.cvut.fel.nss.SaunaStudio.mapper.SaunaStudioMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Služba pro správu cenových seznamů.
 * <p>
 * Třída poskytuje metody pro vytváření a vyhledávání cenových seznamů pro sauny.
 * </p>
 */
@Service
public class PriceListService {

    private final PriceListDao priceListDao;
    private final PriceListMapper priceListMapper;
    private final SaunaStudioMapper saunaStudioMapper;

    /**
     * Vytváří instanci {@link PriceListService}.
     *
     * @param priceListDao DAO pro správu cenových seznamů
     * @param priceListMapper Mapa pro převod mezi {@link PriceListBO} a {@link PriceList}
     * @param saunaStudioMapper Mapa pro převod mezi {@link SaunaStudioBO} a {@link SaunaStudio}
     */
    @Autowired
    public PriceListService(PriceListDao priceListDao, PriceListMapper priceListMapper, SaunaStudioMapper saunaStudioMapper) {
        this.priceListDao = priceListDao;
        this.priceListMapper = priceListMapper;
        this.saunaStudioMapper = saunaStudioMapper;
    }

    /**
     * Vytvoří nový cenový seznam.
     * <p>
     * Tato metoda uloží nový cenový seznam do databáze, pokud dané studio ještě nemá cenový seznam.
     * </p>
     *
     * @param priceListBO Business objekt obsahující informace o cenovém seznamu
     * @return Vytvořený {@link PriceList} cenový seznam
     * @throws MyException Pokud studio již má cenový seznam
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public PriceList create(PriceListBO priceListBO) {
        Objects.requireNonNull(priceListBO);
        PriceList priceList = priceListMapper.priceListBoToPriceList(priceListBO);
        if (this.priceListDao.findBySaunaStudio(priceListBO.getSaunaStudio()) != null)
            throw new MyException("This Studio already has a price list.");
        this.priceListDao.persist(priceList);
        return priceList;
    }

    /**
     * Získá všechny cenové seznamy.
     * <p>
     * Tato metoda vrací seznam všech cenových seznamů v databázi.
     * </p>
     *
     * @return List všech {@link PriceList} cenových seznamů
     */
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    @Transactional
    public List<PriceList> findAll() {
        return priceListDao.findAll();
    }

    /**
     * Najde cenový seznam podle sauny.
     * <p>
     * Tato metoda vrací cenový seznam pro specifikované studio.
     * </p>
     *
     * @param studioBO Business objekt obsahující informace o studiu
     * @return {@link PriceList} cenový seznam pro dané studio
     */
    @Transactional
    public PriceList findBySaunaStudio(SaunaStudioBO studioBO) {
        return priceListDao.findBySaunaStudio(saunaStudioMapper.saunaStudioBoToSaunaStudio(studioBO));
    }
}
