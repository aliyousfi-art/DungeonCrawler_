package com.dnd.model.equipment;

import java.util.Objects;

public abstract class EquipementOffensif {

    private final String name;
    private final int attackBonus;

    protected EquipementOffensif(String name, int attackBonus) {
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
