package com.dnd.model.equipment;

import com.dnd.board.Tile;

/** An elixir — defensive equipment for Wizards. */
public final class Elixir extends DefensiveEquipment implements Tile {

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
