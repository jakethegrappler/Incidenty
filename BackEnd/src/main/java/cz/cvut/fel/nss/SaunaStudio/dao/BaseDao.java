package cz.cvut.fel.nss.SaunaStudio.dao;

import cz.cvut.fel.nss.SaunaStudio.exception.PersistenceException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Abstraktní základní třída pro Data Access Object (DAO).
 * Tato třída poskytuje základní implementaci pro standardní operace s entitami
 * v databázi prostřednictvím JPA. Třída je generická a může být použita pro různé
 * typy entit.
 *
 * @param <T> Typ entity, se kterou DAO pracuje.
 */
public abstract class BaseDao<T> implements GenericDao<T> {

    @PersistenceContext
    protected EntityManager em;

    protected final Class<T> type;

    /**
     * Konstruktor pro inicializaci {@link BaseDao} s daným typem entity.
     *
     * @param type Třída entity, kterou DAO spravuje.
     */
    protected BaseDao(Class<T> type) {
        this.type = type;
    }

    /**
     * Vyhledává entitu podle jejího ID.
     *
     * @param id ID entity, kterou je třeba vyhledat.
     * @return Nalezená entita, nebo {@code null}, pokud entita neexistuje.
     * @throws NullPointerException Pokud je {@code id} {@code null}.
     */
    @Override
    public T find(Integer id) {
        Objects.requireNonNull(id);
        return em.find(type, id);
    }

    /**
     * Vyhledává všechny entity daného typu.
     *
     * @return Seznam všech entit daného typu.
     * @throws PersistenceException Pokud dojde k chybě při dotazování.
     */
    @Override
    public List<T> findAll() {
        try {
            return em.createQuery("SELECT e FROM " + type.getSimpleName() + " e", type).getResultList();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Ukládá novou entitu do databáze.
     *
     * @param entity Entita, kterou je třeba uložit.
     * @throws NullPointerException Pokud je {@code entity} {@code null}.
     * @throws PersistenceException Pokud dojde k chybě při ukládání.
     */
    @Override
    public void persist(T entity) {
        Objects.requireNonNull(entity);
        try {
            em.persist(entity);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Ukládá kolekci entit do databáze.
     *
     * @param entities Kolekce entit, které je třeba uložit.
     * @throws NullPointerException Pokud je {@code entities} {@code null}.
     * @throws PersistenceException Pokud dojde k chybě při ukládání.
     */
    @Override
    public void persist(Collection<T> entities) {
        Objects.requireNonNull(entities);
        if (entities.isEmpty()) {
            return;
        }
        try {
            entities.forEach(this::persist);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Aktualizuje existující entitu v databázi.
     *
     * @param entity Entita, kterou je třeba aktualizovat.
     * @return Aktualizovaná entita.
     * @throws NullPointerException Pokud je {@code entity} {@code null}.
     * @throws PersistenceException Pokud dojde k chybě při aktualizaci.
     */
    @Override
    public T update(T entity) {
        Objects.requireNonNull(entity);
        try {
            return em.merge(entity);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Odstraňuje entitu z databáze.
     *
     * @param entity Entita, kterou je třeba odstranit.
     * @throws NullPointerException Pokud je {@code entity} {@code null}.
     * @throws PersistenceException Pokud dojde k chybě při odstraňování.
     */
    @Override
    public void remove(T entity) {
        Objects.requireNonNull(entity);
        try {
            final T toRemove = em.merge(entity);
            if (toRemove != null) {
                em.remove(toRemove);
            }
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Zjistí, zda entita s daným ID existuje v databázi.
     *
     * @param id ID entity, kterou je třeba zkontrolovat.
     * @return {@code true}, pokud entita existuje; {@code false} jinak.
     */
    @Override
    public boolean exists(Integer id) {
        return id != null && em.find(type, id) != null;
    }
}
