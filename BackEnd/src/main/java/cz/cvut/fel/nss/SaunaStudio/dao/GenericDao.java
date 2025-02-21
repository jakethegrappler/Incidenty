package cz.cvut.fel.nss.SaunaStudio.dao;

import java.util.Collection;
import java.util.List;

/**
 * Základní rozhraní pro data access objekty (DAO).
 *
 * <p>Poskytuje základní CRUD operace pro entitu typu {@code T}.</p>
 *
 * @param <T> Typ entity, na kterou se DAO vztahuje
 */
public interface GenericDao<T> {

    /**
     * Vyhledává instanci entity podle zadaného identifikátoru.
     *
     * @param id Identifikátor entity
     * @return Instance entity nebo {@code null}, pokud taková instance neexistuje
     */
    T find(Integer id);

    /**
     * Vyhledává všechny instance specifikované třídy.
     *
     * @return Seznam instancí, může být prázdný
     */
    List<T> findAll();

    /**
     * Uloží specifikovanou entitu.
     *
     * @param entity Entita k uložení
     */
    void persist(T entity);

    /**
     * Uloží specifikované instance.
     *
     * @param entities Entity k uložení
     */
    void persist(Collection<T> entities);

    /**
     * Aktualizuje specifikovanou entitu.
     *
     * @param entity Entita k aktualizaci
     * @return Aktualizovaná instance
     */
    T update(T entity);

    /**
     * Odstraní specifikovanou entitu.
     *
     * @param entity Entita k odstranění
     */
    void remove(T entity);

    /**
     * Zkontroluje, zda entita s daným identifikátorem existuje (a má typ spravovaný tímto DAO).
     *
     * @param id Identifikátor entity
     * @return {@literal true}, pokud entita existuje, jinak {@literal false}
     */
    boolean exists(Integer id);
}
