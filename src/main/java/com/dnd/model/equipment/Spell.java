package com.dnd.model.equipment;

import com.dnd.board.Tile;

/**
 * A spell: offensive equipment used by wizards that can also occupy a board tile.
 */
public final class Spell extends OffensiveEquipment implements Tile {

    /**
     * @param name        the spell name
     * @param attackBonus the attack bonus provided
     */
    public Spell(String name, int attackBonus) {
        super(name, attackBonus);
    }

    @Override
    public String describe() {
        return toString();
    }

    @Override
    public String toString() {
        return "Spell: " + getName() + ", Attack +" + getAttackBonus();
    }
}
