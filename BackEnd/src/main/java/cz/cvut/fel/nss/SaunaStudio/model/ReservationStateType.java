package cz.cvut.fel.nss.SaunaStudio.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ReservationStateType {

    CANCELED("CANCELED_RESERVATION"), ATTENDED("ATTENDED_RESERVATION"), EXPIRED("EXPIRED_RESERVATION"), BOOKED("BOOKED");

    private final String name;

    @Override
    public String toString() {
        return name;
    }

}
