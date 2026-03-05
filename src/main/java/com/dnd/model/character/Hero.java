package com.dnd.model.character;

import com.dnd.model.CharacterType;
import com.dnd.model.equipment.DefensiveEquipment;
import com.dnd.model.equipment.OffensiveEquipment;

import java.util.Objects;

/**
 * Base class for every playable character.
 * Holds shared state (name, life points, base attack) and the offensive and
 * defensive equipment, along with the validation rules common to all heroes.
 */
public abstract class Hero {

    private String name;
    private int lifePoints;
    private int baseAttack;

    private OffensiveEquipment offensiveEquipment;
    private DefensiveEquipment defensiveEquipment;

    /**
     * Creates a character with the given stats and equipment.
     *
     * @param name               the character name (must not be blank)
     * @param lifePoints         the starting life points (must be &gt;= 0)
     * @param baseAttack         the base attack value (must be &gt;= 0)
     * @param offensiveEquipment the offensive equipment (must not be null)
     * @param defensiveEquipment the defensive equipment (must not be null)
     */
    protected Hero(
            String name,
            int lifePoints,
            int baseAttack,
            OffensiveEquipment offensiveEquipment,
            DefensiveEquipment defensiveEquipment
    ) {
        this.name = requireNonBlank(name);
        this.lifePoints = requireNonNegative(lifePoints, "lifePoints");
        this.baseAttack = requireNonNegative(baseAttack, "baseAttack");
        this.offensiveEquipment = Objects.requireNonNull(offensiveEquipment);
        this.defensiveEquipment = Objects.requireNonNull(defensiveEquipment);
    }

    /**
     * @return the concrete archetype of this character
     */
    public abstract CharacterType getType();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = requireNonBlank(name);
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = requireNonNegative(lifePoints, "lifePoints");
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

    /**
     * @return the base attack plus the offensive equipment bonus
     */
    public int getTotalAttack() {
        return baseAttack + offensiveEquipment.getAttackBonus();
    }

    @Override
    public String toString() {
        return "Hero: " + name +
                ", Type: " + getType() +
                ", Life: " + lifePoints +
                ", Attack: " + getTotalAttack();
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
