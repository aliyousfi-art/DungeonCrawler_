package com.dnd.model.equipment;

import com.dnd.board.Tile;

/**
 * A weapon: offensive equipment used by warriors that can also occupy a board tile.
 */
public final class Weapon extends OffensiveEquipment implements Tile {

    /**
     * @param name        the weapon name
     * @param attackBonus the attack bonus provided
     */
    public Weapon(String name, int attackBonus) {
        super(name, attackBonus);
    }

    @Override
    public String describe() {
        return toString();
    }

    @Override
    public String toString() {
        return "Weapon: " + getName() + ", Attack +" + getAttackBonus();
    }
}
