package com.dnd.model;

import com.dnd.model.character.Warrior;
import com.dnd.model.character.Wizard;
import com.dnd.model.CharacterType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    @Test
    void warrior_starts_with_full_hp() {
        var w = new Warrior("Aragorn");
        assertEquals(10, w.getLifePoints());
    }

    @Test
    void warrior_type_is_warrior() {
        var w = new Warrior("Aragorn");
        assertEquals(CharacterType.WARRIOR, w.getType());
    }

    @Test
    void wizard_type_is_wizard() {
        var w = new Wizard("Gandalf");
        assertEquals(CharacterType.WIZARD, w.getType());
    }

    @Test
    void wizard_has_higher_base_attack_than_warrior() {
        var warrior = new Warrior("Aragorn");
        var wizard = new Wizard("Gandalf");
        assertTrue(wizard.getTotalAttack() > warrior.getTotalAttack());
    }

    @Test
    void blank_name_throws() {
        assertThrows(IllegalArgumentException.class, () -> new Warrior(""));
    }

    @Test
    void life_points_cannot_exceed_max() {
        var w = new Warrior("Aragorn");
        w.setLifePoints(999);
        assertEquals(w.getMaxLifePoints(), w.getLifePoints());
    }
}
