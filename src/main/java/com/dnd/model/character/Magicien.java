package com.dnd.model.character;

import com.dnd.model.CharacterType;
import com.dnd.model.equipment.Philtre;
import com.dnd.model.equipment.Sort;

public final class Magicien extends Personnage {

    public Magicien(String name) {
        super(
                name,
                6,
                6,
                15,
                new Sort("Sort de base", 0),
                new Philtre("Philtre de base", 0)
        );
    }

    @Override
    public CharacterType getType() {
        return CharacterType.WIZARD;
    }
}
