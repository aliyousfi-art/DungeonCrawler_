package com.dnd.model.equipment;

import com.dnd.board.Tile;

/**
 * A shield: defensive equipment used by warriors that can also occupy a board tile.
 */
public final class Shield extends DefensiveEquipment implements Tile {

    public Shield(String name, int defenseBonus) {
        super(name, defenseBonus);
    }

    @Override
    public String describe() {
        return toString();
    }

    @Override
    public String toString() {
        return "Shield: " + getName() + ", Defense +" + getDefenseBonus();
    }
}
