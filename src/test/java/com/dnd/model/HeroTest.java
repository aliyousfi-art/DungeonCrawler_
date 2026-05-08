package com.dnd.model;

import com.dnd.model.character.Warrior;
import com.dnd.model.character.Wizard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    @Test
    void warrior_has_correct_base_attack() {
        var warrior = new Warrior("Test Warrior");

        assertEquals(10, warrior.getBaseAttack());
    }

    @Test
    void warrior_has_correct_life_points() {
        var warrior = new Warrior("Test Warrior");

        assertEquals(10, warrior.getLifePoints());
    }

    @Test
    void wizard_has_correct_life_points() {
        var wizard = new Wizard("Test Wizard");

        assertEquals(6, wizard.getLifePoints());
    }

    @Test
    void wizard_has_higher_attack_than_warrior() {
        var warrior = new Warrior("Warrior");
        var wizard = new Wizard("Wizard");

        assertTrue(wizard.getBaseAttack() > warrior.getBaseAttack());
    }

    @Test
    void different_characters_have_different_types() {
        var warrior = new Warrior("Warrior");
        var wizard = new Wizard("Wizard");

        assertNotEquals(warrior.getType(), wizard.getType());
    }

    @Test
    void character_name_validation() {
        assertThrows(IllegalArgumentException.class, () -> new Warrior(""));
    }

    @Test
    void character_can_take_damage() {
        var warrior = new Warrior("Damaged");
        warrior.setLifePoints(5);

        assertEquals(5, warrior.getLifePoints());
    }
}
