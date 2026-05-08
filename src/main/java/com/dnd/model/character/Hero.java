package com.dnd.model.character;

import com.dnd.model.CharacterType;
import com.dnd.model.equipment.DefensiveEquipment;
import com.dnd.model.equipment.OffensiveEquipment;

import java.util.Objects;

/**
 * Base class for all playable characters.
 * Holds life points, base attack and equipped items.
 */
public abstract class Hero {

    private final int maxLifePoints;

    private String name;
    private int lifePoints;
    private int baseAttack;

    private OffensiveEquipment offensiveEquipment;
    private DefensiveEquipment defensiveEquipment;

    protected Hero(
            String name,
            int maxLifePoints,
            int lifePoints,
            int baseAttack,
            OffensiveEquipment offensiveEquipment,
            DefensiveEquipment defensiveEquipment
    ) {
        this.name = requireNonBlank(name);
        this.maxLifePoints = requireNonNegative(maxLifePoints, "maxLifePoints");
        this.baseAttack = requireNonNegative(baseAttack, "baseAttack");
        this.offensiveEquipment = Objects.requireNonNull(offensiveEquipment);
        this.defensiveEquipment = Objects.requireNonNull(defensiveEquipment);

        this.lifePoints = clampLifePoints(lifePoints);
    }

    public abstract CharacterType getType();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = requireNonBlank(name);
    }

    public int getMaxLifePoints() {
        return maxLifePoints;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = clampLifePoints(lifePoints);
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = requireNonNegative(baseAttack, "baseAttack");
    }

    public OffensiveEquipment getOffensiveEquipment() {
        return offensiveEquipment;
    }

    public void setOffensiveEquipment(OffensiveEquipment offensiveEquipment) {
        this.offensiveEquipment = Objects.requireNonNull(offensiveEquipment);
    }

    public DefensiveEquipment getDefensiveEquipment() {
        return defensiveEquipment;
    }

    public void setDefensiveEquipment(DefensiveEquipment defensiveEquipment) {
        this.defensiveEquipment = Objects.requireNonNull(defensiveEquipment);
    }

    public int getTotalAttack() {
        return baseAttack + offensiveEquipment.getAttackBonus();
    }

    @Override
    public String toString() {
        return "Hero: " + name +
                ", Type: " + getType() +
                ", HP: " + lifePoints +
                ", Attack: " + getTotalAttack();
    }

    private static String requireNonBlank(String value) {
        Objects.requireNonNull(value);
        if (value.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        return value;
    }

    private int clampLifePoints(int lifePoints) {
        int nonNegative = requireNonNegative(lifePoints, "lifePoints");
        return Math.min(nonNegative, maxLifePoints);
    }

    private static int requireNonNegative(int value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " must be >= 0");
        }
        return value;
    }
}
