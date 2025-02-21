package cz.cvut.fel.MuzeumSys.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Business Object (BO) třída reprezentující administrátora v systému.
 * Tato třída obsahuje obchodní logiku a může být rozšířena o další
 * metody a operace, které jsou specifické pro správu administrátorských účtů.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminBO {

    /**
     * Uživatelské jméno administrátora.
     */
    private String username;

    /**
     * Heslo administrátora.
     */
    private String password;

}
