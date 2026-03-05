package com.dnd.model.equipment;

import com.dnd.board.Case;

public final class Bouclier extends EquipementDefensif implements Case {

    public Bouclier(String name, int defenseBonus) {
        super(name, defenseBonus);
    }

    @Override
    public String describe() {
        return toString();
    }

    @Override
    public String toString() {
        return "Bouclier : " + getName() + ", Défense +" + getDefenseBonus();
    }
}
