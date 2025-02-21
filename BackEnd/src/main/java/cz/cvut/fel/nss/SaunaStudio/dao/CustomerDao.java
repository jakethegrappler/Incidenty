package cz.cvut.fel.nss.SaunaStudio.dao;

import cz.cvut.fel.nss.SaunaStudio.model.Employee;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object (DAO) pro entitu {@link Employee}.
 * Tato třída poskytuje metody pro přístup a manipulaci s daty zákazníků
 * v databázi prostřednictvím JPA.
 */
@Repository
public class CustomerDao extends BaseDao<Employee> {

    /**
     * Konstruktor pro inicializaci {@link CustomerDao} s entitou {@link Employee}.
     */
    protected CustomerDao() {
        super(Employee.class);
    }

    /**
     * Vyhledává zákazníka podle uživatelského jména.
     *
     * <p>Pokud není zákazník nalezen, metoda vrací {@code null}.</p>
     *
     * @param username Uživatelské jméno zákazníka, které se má hledat.
     * @return {@link Employee} nalezený podle uživatelského jména, nebo {@code null}, pokud zákazník neexistuje.
     */
    public Employee findByUsername(String username) {
        try {
            return em.createNamedQuery("Customer.findByUsername", Employee.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Vyhledává zákazníka podle e-mailové adresy.
     *
     * <p>Pokud není zákazník nalezen, metoda vrací {@code null}.</p>
     *
     * @param email E-mailová adresa zákazníka, kterou se má hledat.
     * @return {@link Employee} nalezený podle e-mailové adresy, nebo {@code null}, pokud zákazník neexistuje.
     */
    public Employee findByEmail(String email) {
        try {
            return em.createNamedQuery("Customer.findByEmail", Employee.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Vyhledává zákazníka podle telefonního čísla.
     *
     * <p>Pokud není zákazník nalezen, metoda vrací {@code null}.</p>
     *
     * @param phoneNumber Telefonní číslo zákazníka, které se má hledat.
     * @return {@link Employee} nalezený podle telefonního čísla, nebo {@code null}, pokud zákazník neexistuje.
     */
    public Employee findByPhoneNumber(String phoneNumber) {
        try {
            return em.createNamedQuery("Customer.findByPhoneNumber", Employee.class)
                    .setParameter("phoneNumber", phoneNumber)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
