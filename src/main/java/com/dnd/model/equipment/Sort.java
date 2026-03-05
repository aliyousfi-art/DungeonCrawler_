package com.dnd.model.equipment;

import com.dnd.board.Case;
import com.dnd.board.InteractionResult;
import com.dnd.model.character.Magicien;
import com.dnd.model.character.Personnage;

public class Sort extends EquipementOffensif implements Case {

    public Sort(String name, int attackBonus) {
        super(name, attackBonus);
    }

    @Override
    public String describe() {
        return toString();
    }

    @Override
    public InteractionResult interaction(Personnage personnage) {
        if (!(personnage instanceof Magicien)) {
            System.out.println("This spell is not compatible with your class.");
            return InteractionResult.KEEP_TILE;
        }

        int currentBonus = personnage.getEquipementOffensif().getAttackBonus();
        if (getAttackBonus() <= currentBonus) {
            System.out.printf("You already have a better (or equal) spell. Current bonus=%d, new bonus=%d%n",
                    currentBonus,
                    getAttackBonus());
            return InteractionResult.KEEP_TILE;
        }

        personnage.setEquipementOffensif(this);
        System.out.printf("You learn %s (attack bonus +%d).%n", getName(), getAttackBonus());
        return InteractionResult.REMOVE_TILE;
    }

    @Override
    public String toString() {
        return "Sort : " + getName() + ", Attaque +" + getAttackBonus();
    }
}
