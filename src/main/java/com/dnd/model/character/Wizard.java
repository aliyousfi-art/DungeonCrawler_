package com.dnd.model.character;

import com.dnd.model.CharacterType;
import com.dnd.model.equipment.Elixir;
import com.dnd.model.equipment.Spell;

public final class Wizard extends Hero {

    public Wizard(String name) {
        super(
                name,
                6,
                6,
                15,
                new Spell("Basic spell", 0),
                new Elixir("Basic elixir", 0)
        );
    }

    @Override
    public CharacterType getType() {
        return CharacterType.WIZARD;
    }
}
