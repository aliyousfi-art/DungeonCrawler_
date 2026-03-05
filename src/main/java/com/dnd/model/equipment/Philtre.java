package com.dnd.model.equipment;

import com.dnd.board.Case;

public final class Philtre extends EquipementDefensif implements Case {

    public Philtre(String name, int defenseBonus) {
        super(name, defenseBonus);
    }

    @Override
    public String describe() {
        return toString();
    }

    @Override
    public String toString() {
        return "Philtre : " + getName() + ", Défense +" + getDefenseBonus();
    }
}
