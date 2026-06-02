package com.dnd.model.equipment;

import java.util.Objects;

/**
 * Base class for offensive equipment, carrying a name and an attack bonus.
 */
public abstract class OffensiveEquipment {

    private final String name;
    private final int attackBonus;

    protected OffensiveEquipment(String name, int attackBonus) {
        this.name = requireNonBlank(name);
        this.attackBonus = attackBonus;
    }

    /** @return the equipment name */
    public String getName() {
        return name;
    }

    /** @return the bonus added to the wielder's attack */
    public int getAttackBonus() {
        return attackBonus;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{name='" + name + "', attackBonus=" + attackBonus + "}";
    }

    private static String requireNonBlank(String value) {
        Objects.requireNonNull(value);
        if (value.isBlank()) throw new IllegalArgumentException("name must not be blank");
        return value;
    }
}
