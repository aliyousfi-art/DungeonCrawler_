package com.dnd.model.character;

import com.dnd.model.CharacterType;
import com.dnd.model.equipment.Elixir;
import com.dnd.model.equipment.Spell;

/**
 * A wizard character class.
 * Wizards have high attack but lower health compared to warriors.
 */
public final class Wizard extends Hero {

    /**
     * Creates a new wizard with the specified name.
     *
     * @param name the wizard's name
     */
    public Wizard(String name) {
        super(name, 6, 15, new Spell("Basic Spell", 0), new Elixir("Basic Elixir", 0));
    }

    @Override
    public CharacterType getType() {
        return CharacterType.WIZARD;
    }
}
