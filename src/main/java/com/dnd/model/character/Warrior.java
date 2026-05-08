package com.dnd.model.character;

import com.dnd.model.CharacterType;
import com.dnd.model.equipment.Shield;
import com.dnd.model.equipment.Weapon;

/**
 * Warrior hero — high HP, starts with a basic weapon and shield.
 */
public final class Warrior extends Hero {

    public Warrior(String name) {
        super(
                name,
                10,
                10,
                10,
                new Weapon("Basic weapon", 0),
                new Shield("Basic shield", 0)
        );
    }

    @Override
    public CharacterType getType() {
        return CharacterType.WARRIOR;
    }
}
