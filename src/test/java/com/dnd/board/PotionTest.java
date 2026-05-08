package com.dnd.board;

import com.dnd.model.character.Warrior;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PotionTest {

    @Test
    void standard_potion_heals_two_hp() {
        var hero = new Warrior("Arthur");
        hero.setLifePoints(5);

        new StandardPotion().interaction(hero);

        assertEquals(7, hero.getLifePoints());
    }

    @Test
    void potion_does_not_exceed_max_hp() {
        var hero = new Warrior("Arthur");
        // hero starts at max HP (10)

        new StandardPotion().interaction(hero);

        assertEquals(hero.getMaxLifePoints(), hero.getLifePoints());
    }

    @Test
    void large_potion_heals_more_than_standard() {
        var hero1 = new Warrior("A");
        var hero2 = new Warrior("B");
        hero1.setLifePoints(1);
        hero2.setLifePoints(1);

        new StandardPotion().interaction(hero1);
        new LargePotion().interaction(hero2);

        assertTrue(hero2.getLifePoints() > hero1.getLifePoints());
    }
}
