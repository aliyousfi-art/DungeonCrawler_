package com.dnd.model.character;

import com.dnd.model.CharacterType;
import com.dnd.model.equipment.Shield;
import com.dnd.model.equipment.Weapon;

/**
 * A warrior character class.
 * Warriors have balanced health and attack stats.
 */
public final class Warrior extends Hero {

    /**
     * Creates a new warrior with the specified name.
     *
     * @param name the warrior's name
     */
    public Warrior(String name) {
        super(name, 10, 10, new Weapon("Basic Weapon", 0), new Shield("Basic Shield", 0));
    }

    @Override
    public CharacterType getType() {
        return CharacterType.WARRIOR;
    }
}
