package com.dnd.model.character;

import com.dnd.model.CharacterType;
import com.dnd.model.equipment.EquipementDefensif;
import com.dnd.model.equipment.EquipementOffensif;

import java.util.Objects;

public abstract class Personnage {

    private final int maxLifePoints;

    private String name;
    private int lifePoints;
    private int baseAttack;

    private EquipementOffensif equipementOffensif;
    private EquipementDefensif equipementDefensif;

    protected Personnage(
            String name,
            int maxLifePoints,
            int lifePoints,
            int baseAttack,
            EquipementOffensif equipementOffensif,
            EquipementDefensif equipementDefensif
    ) {
        this.name = requireNonBlank(name);
        this.maxLifePoints = requireNonNegative(maxLifePoints, "maxLifePoints");
        this.baseAttack = requireNonNegative(baseAttack, "baseAttack");
        this.equipementOffensif = Objects.requireNonNull(equipementOffensif);
        this.equipementDefensif = Objects.requireNonNull(equipementDefensif);

        // clamp life points to [0; maxLifePoints]
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

    public EquipementOffensif getEquipementOffensif() {
        return equipementOffensif;
    }

    public void setEquipementOffensif(EquipementOffensif equipementOffensif) {
        this.equipementOffensif = Objects.requireNonNull(equipementOffensif);
    }

    public EquipementDefensif getEquipementDefensif() {
        return equipementDefensif;
    }

    public void setEquipementDefensif(EquipementDefensif equipementDefensif) {
        this.equipementDefensif = Objects.requireNonNull(equipementDefensif);
    }

    public int getTotalAttack() {
        return baseAttack + equipementOffensif.getAttackBonus();
    }

    @Override
    public String toString() {
        return "Personnage : " + name +
                ", Type : " + getType() +
                ", Niveau de vie : " + lifePoints +
                ", Force : " + getTotalAttack();
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
