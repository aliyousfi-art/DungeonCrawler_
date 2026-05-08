package com.dnd.board;

import com.dnd.model.character.Hero;

import java.util.Objects;

public class Potion implements Tile {

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
        return "Potion: " + name + ", Heal=" + healPoints;
    }

    @Override
    public InteractionResult interaction(Hero hero) {
        int before = hero.getLifePoints();

        int healed = Math.min(hero.getMaxLifePoints(), before + healPoints);
        hero.setLifePoints(healed);

        int after = hero.getLifePoints();
        System.out.printf("You drink %s: HP %d -> %d%n", name, before, after);

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
