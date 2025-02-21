package cz.cvut.fel.nss.SaunaStudio.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SaunaType {

    FINNISH("FINNISH_SAUNA"),
    HERBAL("HERBAL_SAUNA"),
    STEAM("STEAM_SAUNA"),
    SALT("SALT_SAUNA"),
    TROPICAL("TROPICAL_SAUNA"),
    INFRA_RED("INFRA_RED_SAUNA");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
