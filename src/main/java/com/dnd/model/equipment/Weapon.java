package com.dnd.model.equipment;

import com.dnd.board.InteractionResult;
import com.dnd.board.Tile;
import com.dnd.model.character.Hero;
import com.dnd.model.character.Warrior;

public class Weapon extends OffensiveEquipment implements Tile {

    public Weapon(String name, int attackBonus) {
        super(name, attackBonus);
    }

    @Override
    public String describe() {
        return toString();
    }

    @Override
    public InteractionResult interaction(Hero hero) {
        if (!(hero instanceof Warrior)) {
            System.out.println("This weapon is not compatible with your class.");
            return InteractionResult.KEEP_TILE;
        }

        int currentBonus = hero.getOffensiveEquipment().getAttackBonus();
        if (getAttackBonus() <= currentBonus) {
            System.out.printf("You already have a better (or equal) weapon. Current bonus=%d, new bonus=%d%n",
                    currentBonus,
                    getAttackBonus());
            return InteractionResult.KEEP_TILE;
        }

        hero.setOffensiveEquipment(this);
        System.out.printf("You equip %s (attack bonus +%d).%n", getName(), getAttackBonus());
        return InteractionResult.REMOVE_TILE;
    }

    @Override
    public String toString() {
        return "Weapon: " + getName() + ", Attack +" + getAttackBonus();
    }
}
