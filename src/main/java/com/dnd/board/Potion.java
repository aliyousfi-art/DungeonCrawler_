package com.dnd.board;

import com.dnd.model.character.Personnage;

import java.util.Objects;

public class Potion implements Case {

    private final String name;
    private final int healPoints;

    protected Potion(String name, int healPoints) {
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
        return "Potion : " + name + ", Soin=" + healPoints;
    }

    @Override
    public InteractionResult interaction(Personnage personnage) {
        int before = personnage.getLifePoints();

        int healed = Math.min(personnage.getMaxLifePoints(), before + healPoints);
        personnage.setLifePoints(healed);

        int after = personnage.getLifePoints();
        System.out.printf("You drink %s: PV %d -> %d%n", name, before, after);

        // A potion is consumed once used.
        return InteractionResult.REMOVE_TILE;
    }

    @Override
    public String toString() {
        return describe();
    }

    private static String requireNonBlank(String value) {
        Objects.requireNonNull(value);
        if (value.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        return value;
    }

    private static int requireNonNegative(int value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " must be >= 0");
        }
        return value;
    }
}
