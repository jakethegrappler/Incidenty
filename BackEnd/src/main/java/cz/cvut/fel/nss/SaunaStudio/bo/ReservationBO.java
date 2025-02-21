package cz.cvut.fel.nss.SaunaStudio.bo;

import cz.cvut.fel.nss.SaunaStudio.dao.EventDao;
import cz.cvut.fel.nss.SaunaStudio.exception.MyException;
import cz.cvut.fel.nss.SaunaStudio.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Business Object (BO) třída, která reprezentuje rezervaci události v sauna studiu.
 * Tato třída obsahuje informace o rezervaci, jako je událost, časy, počet osob, stav rezervace,
 * sauna studio a zákazník.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationBO {

    /**
     * Událost, která je rezervována.
     */
    private Event event;

    /**
     * Konečný čas rezervace.
     */
    private LocalDateTime endTime;

    /**
     * Počet osob, které se zúčastní rezervace.
     */
    private int numOfPeople;

    /**
     * Počáteční čas rezervace.
     */
    private LocalDateTime startTime;

    /**
     * Stav rezervace (např. potvrzeno, zrušeno).
     */
    private String stateType;

    /**
     * Sauna studio, ve kterém se rezervace koná.
     */
    private SaunaStudio saunaStudio;

    /**
     * Zákazník, který rezervaci provedl.
     */
    private Employee employee;

    /**
     * Validuje rezervaci. Kontroluje platnost události a časy rezervace.
     *
     * @param eventDao DAO pro práci s událostmi, používané pro aktualizaci události.
     * @throws MyException Pokud jsou časy neplatné nebo kapacita události není dostatečná.
     */
    public void validate(EventDao eventDao) {
        eventValid(this.getEvent(), eventDao);
        timeValid(this.getStartTime(), this.getEndTime());
    }

    /**
     * Validuje časy rezervace. Zajišťuje, že počáteční čas je před konečným časem
     * a počáteční čas je v budoucnosti.
     *
     * @param Stime Počáteční čas rezervace.
     * @param Etime Konečný čas rezervace.
     * @throws MyException Pokud konečný čas není po počátečním čase nebo počáteční čas není v budoucnosti.
     */
    private void timeValid(LocalDateTime Stime, LocalDateTime Etime) {
        if (!(Stime.isBefore(Etime) && Stime.isBefore(LocalDateTime.now())))
            throw new MyException("End time should be after start time or start time should be in future");
    }

    /**
     * Validuje událost rezervace. Zajišťuje, že událost existuje a má dostatečnou kapacitu.
     *
     * @param event Událost, která má být validována.
     * @param eventDao DAO pro práci s událostmi, používané pro aktualizaci události.
     * @throws MyException Pokud událost je null nebo její kapacita není dostatečná.
     */
    private void eventValid(Event event, EventDao eventDao) {
        if (event == null) {
            throw new MyException("Event is null");
        } else {
            event.setFreeCapacity(event.getFreeCapacity() - this.getNumOfPeople());
            eventDao.update(event);
            if (!(event.getFreeCapacity() >= 0))
                throw new MyException("Free capacity should be greater than 0");
        }
    }
}
