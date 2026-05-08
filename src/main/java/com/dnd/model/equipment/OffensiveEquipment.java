package com.dnd.model.equipment;

import java.util.Objects;

/**
 * Base class for all offensive equipment (weapons and spells).
 * Provides an attack bonus added on top of the hero's base attack.
 */
public abstract class OffensiveEquipment {

    private final String name;
    private final int attackBonus;

    protected OffensiveEquipment(String name, int attackBonus) {
        this.name = requireNonBlank(name);
        this.attackBonus = attackBonus;
    }

    public String getName() {
        return name;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", attackBonus=" + attackBonus +
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
