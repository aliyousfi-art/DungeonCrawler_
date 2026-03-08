package com.dnd.board;

import java.util.Objects;

/**
 * A potion occupying a board tile, healing the hero by a fixed amount.
 */
public final class Potion implements Tile {

    private final String name;
    private final int healPoints;

    /**
     * Creates a potion.
     *
     * @param name       the potion name (must not be blank)
     * @param healPoints the amount of life points restored (must be &gt;= 0)
     */
    public Potion(String name, int healPoints) {
        this.name = requireNonBlank(name);
        this.healPoints = requireNonNegative(healPoints, "healPoints");
    }

    public String getName() {
        return name;
    }

    public int getHealPoints() {
        return healPoints;
    }

    @Override
    public String describe() {
        return "Potion: " + name + ", Heal=" + healPoints;
    }

    @Override
    public String toString() {
        return describe();
    }

    private static String requireNonBlank(String value) {
        Objects.requireNonNull(value);
        if (value.isBlank()) throw new IllegalArgumentException("name must not be blank");
        return value;
    }

    private static int requireNonNegative(int value, String fieldName) {
        if (value < 0) throw new IllegalArgumentException(fieldName + " must be >= 0");
        return value;
    }
}
