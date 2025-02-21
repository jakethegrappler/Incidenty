package cz.cvut.fel.nss.SaunaStudio.service;

import cz.cvut.fel.nss.SaunaStudio.bo.SaunaStudioBO;
import cz.cvut.fel.nss.SaunaStudio.dao.SaunaStudioDao;
import cz.cvut.fel.nss.SaunaStudio.exception.MyException;
import cz.cvut.fel.nss.SaunaStudio.mapper.SaunaStudioMapper;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Služba pro správu saunových studií.
 * <p>
 * Třída poskytuje metody pro vytváření a hledání saunových studií. Je určena pro administrátory a obsluhuje operace
 * týkající se saunových studií v systému.
 * </p>
 */
@Service
public class SaunaStudioService {

    private final SaunaStudioDao saunaStudioDao;
    private final SaunaStudioMapper saunaStudioMapper;

    /**
     * Vytváří instanci {@link SaunaStudioService}.
     *
     * @param saunaStudioDao DAO pro správu saunových studií
     * @param saunaStudioMapper Mapa pro převod mezi {@link SaunaStudioBO} a {@link SaunaStudio}
     */
    @Autowired
    public SaunaStudioService(SaunaStudioDao saunaStudioDao, SaunaStudioMapper saunaStudioMapper) {
        this.saunaStudioDao = saunaStudioDao;
        this.saunaStudioMapper = saunaStudioMapper;
    }

    /**
     * Vytvoří nové saunové studio.
     * <p>
     * Tato metoda ověří, zda saunové studio se stejným názvem nebo URL již existuje. Pokud ne, vytvoří nové saunové studio
     * a uloží ho do databáze.
     * </p>
     *
     * @param saunaStudioBO Business objekt obsahující informace o saunovém studiu
     * @return Vytvořené {@link SaunaStudio} saunové studio
     * @throws NullPointerException Pokud je {@code saunaStudioBO} {@code null}
     * @throws MyException Pokud studio se stejným názvem nebo URL již existuje
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public SaunaStudio create(SaunaStudioBO saunaStudioBO) {
        Objects.requireNonNull(saunaStudioBO);
        SaunaStudio saunaStudio = saunaStudioMapper.saunaStudioBoToSaunaStudio(saunaStudioBO);
        if (this.saunaStudioDao.findByName(saunaStudioBO.getName()) != null)
            throw new MyException("Studio name already exists.");
        if (this.saunaStudioDao.findByURL(saunaStudioBO.getURL()) != null)
            throw new MyException("Studio URL already exists.");
        this.saunaStudioDao.persist(saunaStudio);
        return saunaStudio;
    }

    /**
     * Najde všechna saunová studia.
     *
     * @return List všech {@link SaunaStudio} saunových studií
     */
    @Transactional
    public List<SaunaStudio> findAll() {
        return saunaStudioDao.findAll();
    }

    /**
     * Najde saunové studio podle názvu.
     *
     * @param name Název saunového studia
     * @return {@link SaunaStudio} saunové studio s daným názvem, nebo {@code null}, pokud studio s tímto názvem neexistuje
     */
    @Transactional
    public SaunaStudio findByName(String name) {
        return saunaStudioDao.findByName(name);
    }

    /**
     * Najde saunové studio podle URL.
     *
     * @param url URL saunového studia
     * @return {@link SaunaStudio} saunové studio s danou URL, nebo {@code null}, pokud studio s touto URL neexistuje
     */
    @Transactional
    public SaunaStudio findByURL(String url) {
        return saunaStudioDao.findByURL(url);
    }
}
