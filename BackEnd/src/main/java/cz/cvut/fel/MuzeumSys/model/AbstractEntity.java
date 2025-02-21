package cz.cvut.fel.MuzeumSys.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * Abstraktní základní třída pro všechny entity v aplikaci.
 *
 * <p>Tato třída poskytuje společné vlastnosti pro všechny entity, konkrétně identifikátor {@code id},
 * který je automaticky generován.</p>
 *
 * <p>Každá třída, která dědí z {@code AbstractEntity}, získá {@code id} atribut, který je primárním klíčem
 * a automaticky generován.</p>
 */
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private Integer id;

}
