package com.dnd.model.equipment;

import java.util.Objects;

/**
 * Base class for all defensive equipment (shields and elixirs).
 * Provides a defense bonus for the hero.
 */
public abstract class DefensiveEquipment {

    private final String name;
    private final int defenseBonus;

    protected DefensiveEquipment(String name, int defenseBonus) {
        this.name = requireNonBlank(name);
        this.defenseBonus = defenseBonus;
    }

    public String getName() {
        return name;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", defenseBonus=" + defenseBonus +
                '}';
    }

    private static String requireNonBlank(String value) {
        Objects.requireNonNull(value);
        if (value.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        return value;
    }
}
