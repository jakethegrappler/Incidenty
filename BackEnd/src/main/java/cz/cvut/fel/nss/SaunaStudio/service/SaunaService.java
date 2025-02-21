package cz.cvut.fel.nss.SaunaStudio.service;

import cz.cvut.fel.nss.SaunaStudio.bo.SaunaBO;
import cz.cvut.fel.nss.SaunaStudio.bo.SaunaStudioBO;
import cz.cvut.fel.nss.SaunaStudio.dao.SaunaDao;
import cz.cvut.fel.nss.SaunaStudio.exception.MyException;
import cz.cvut.fel.nss.SaunaStudio.mapper.SaunaMapper;
import cz.cvut.fel.nss.SaunaStudio.mapper.SaunaStudioMapper;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Služba pro správu saun.
 * <p>
 * Třída poskytuje metody pro vytváření a hledání saun. Je určena pro administrátory a obsluhuje operace týkající se saun v systému.
 * </p>
 */
@Service
public class SaunaService {

    private final SaunaDao saunaDao;
    private final SaunaStudioMapper saunaStudioMapper;
    private final SaunaMapper saunaMapper;

    /**
     * Vytváří instanci {@link SaunaService}.
     *
     * @param saunaDao DAO pro správu saun
     * @param saunaStudioMapper Mapa pro převod mezi {@link SaunaStudioBO} a {@link SaunaStudio}
     * @param saunaMapper Mapa pro převod mezi {@link SaunaBO} a {@link Sauna}
     */
    @Autowired
    public SaunaService(SaunaDao saunaDao, SaunaStudioMapper saunaStudioMapper, SaunaMapper saunaMapper) {
        this.saunaDao = saunaDao;
        this.saunaStudioMapper = saunaStudioMapper;
        this.saunaMapper = saunaMapper;
    }

    /**
     * Vytvoří novou saunu.
     * <p>
     * Tato metoda ověří, zda sauna se stejným názvem již existuje a pokud ne, vytvoří novou saunu a uloží ji do databáze.
     * </p>
     *
     * @param saunaBO Business objekt obsahující informace o sauně
     * @return Vytvořená {@link Sauna} sauna
     * @throws NullPointerException Pokud je {@code saunaBO} {@code null}
     * @throws MyException Pokud sauna se stejným názvem již existuje
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public Sauna create(SaunaBO saunaBO) {
        Objects.requireNonNull(saunaBO);
        Sauna sauna = saunaMapper.saunaBoToSauna(saunaBO);
        if (this.saunaDao.findByName(saunaBO.getName()) != null)
            throw new MyException("This Studio already has a price list.");
        this.saunaDao.persist(sauna);
        return sauna;
    }

    /**
     * Najde všechny sauny.
     *
     * @return List všech {@link Sauna} saun
     */
    @Transactional
    public List<Sauna> findAll() {
        return saunaDao.findAll();
    }

    /**
     * Najde sauny podle zadaného saunového studia.
     *
     * @param studioBO Business objekt obsahující informace o saunovém studiu
     * @return List {@link Sauna} saun, které patří k danému saunovému studiu
     */
    @Transactional
    public List<Sauna> findBySaunaStudio(SaunaStudioBO studioBO) {
        return saunaDao.findBySaunaStudio(saunaStudioMapper.saunaStudioBoToSaunaStudio(studioBO));
    }

    /**
     * Najde sauny podle zadaného typu sauny.
     *
     * @param type Typ sauny
     * @return List {@link Sauna} saun s daným typem
     */
    @Transactional
    public List<Sauna> findBySaunaType(SaunaType type) {
        return saunaDao.findBySaunaType(type);
    }

    /**
     * Najde sauny podle maximální teploty.
     *
     * @param maxTemperature Maximální teplota
     * @return List {@link Sauna} saun, které mají maximální teplotu menší nebo rovnu {@code maxTemperature}
     */
    @Transactional
    public List<Sauna> findByMaxTemperature(Integer maxTemperature) {
        return saunaDao.findByMaxTemperature(maxTemperature);
    }
}
