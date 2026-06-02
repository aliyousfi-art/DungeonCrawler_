package com.dnd.model.equipment;

import com.dnd.board.Tile;

/**
 * An elixir: defensive equipment used by wizards that can also occupy a board tile.
 */
public final class Elixir extends DefensiveEquipment implements Tile {

    /**
     * @param name         the elixir name
     * @param defenseBonus the defense bonus provided
     */
    public Elixir(String name, int defenseBonus) {
        super(name, defenseBonus);
    }

    @Override
    public String describe() {
        return toString();
    }

    @Override
    public String toString() {
        return "Elixir: " + getName() + ", Defense +" + getDefenseBonus();
    }
}
