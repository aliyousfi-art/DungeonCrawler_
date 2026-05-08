package com.dnd.board;

import com.dnd.model.character.Warrior;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PotionTest {

    @Test
    void potion_heals_character() {
        var hero = new Warrior("Test");
        hero.setLifePoints(5);
        var potion = new Potion("Healing Potion", 3);

        hero.setLifePoints(hero.getLifePoints() + potion.getHealPoints());

        assertEquals(8, hero.getLifePoints());
    }

    @Test
    void potion_heals_multiple_times() {
        var hero = new Warrior("Test");
        hero.setLifePoints(3);
        var potion = new Potion("Strong Potion", 5);

        hero.setLifePoints(hero.getLifePoints() + potion.getHealPoints());

        assertEquals(8, hero.getLifePoints());
    }

    @Test
    void multiple_potions_stack() {
        var hero = new Warrior("Test");
        var hero2 = new Warrior("Test2");
        var potion = new Potion("Standard Potion", 2);
        var potion2 = new Potion("Strong Potion", 5);

        hero.setLifePoints(hero.getLifePoints() + potion.getHealPoints());
        hero2.setLifePoints(hero2.getLifePoints() + potion2.getHealPoints());

        assertEquals(12, hero.getLifePoints());
        assertEquals(15, hero2.getLifePoints());
    }
}
