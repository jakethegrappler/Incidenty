package cz.cvut.fel.nss.SaunaStudio.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DurationType {

    THREE_HOURS("THREE_HOURS_DURATION"), FULL_DAY("FULL_DAY_DURATION");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
