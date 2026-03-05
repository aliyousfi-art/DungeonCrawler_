package com.dnd.model.character;

import com.dnd.model.CharacterType;
import com.dnd.model.equipment.Arme;
import com.dnd.model.equipment.Bouclier;

public final class Guerrier extends Personnage {

    public Guerrier(String name) {
        super(
                name,
                10,
                10,
                10,
                new Arme("Arme de base", 0),
                new Bouclier("Bouclier de base", 0)
        );
    }

    @Override
    public CharacterType getType() {
        return CharacterType.WARRIOR;
    }
}
