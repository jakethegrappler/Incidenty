package cz.cvut.fel.nss.SaunaStudio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

/**
 * Data Transfer Object (DTO) pro hodinovou obsazenost.
 *
 * <p>Tato třída slouží k přenosu informací o obsazenosti v určité hodině,
 * například při analýze obsazenosti sauny během dne.</p>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HourlyOccupancy {

    /**
     * Časová hodina, pro kterou je udaná obsazenost.
     */
    private LocalTime hour;

    /**
     * Počet osob (obsazenost) v dané hodině.
     */
    private int occupancy;

}
