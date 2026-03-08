package com.dnd.board;

import com.dnd.model.character.Personnage;

import java.util.Objects;

public class Ennemi implements Case {

    private final String name;
    private int lifePoints;
    private final int attack;

    protected Ennemi(String name, int lifePoints, int attack) {
        this.name = requireNonBlank(name);
        this.lifePoints = requireNonNegative(lifePoints, "lifePoints");
        this.attack = requireNonNegative(attack, "attack");
    }

    public String getName() {
        return name;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = requireNonNegative(lifePoints, "lifePoints");
    }

    public int getAttack() {
        return attack;
    }

    @Override
    public String describe() {
        return "Ennemi : " + name + ", PV=" + lifePoints + ", Attaque=" + attack;
    }

    @Override
    public InteractionResult interaction(Personnage personnage) {
        // Combat is handled in iteration 6 by the menu/combat service.
        System.out.println("You encountered an enemy: " + describe());
        return InteractionResult.KEEP_TILE;
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
