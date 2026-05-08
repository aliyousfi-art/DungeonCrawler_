package com.dnd.combat;

import com.dnd.board.Enemy;
import com.dnd.game.Dice;
import com.dnd.model.character.Hero;

import java.util.Objects;
import java.util.Scanner;

/**
 * Handles turn-based combat between a hero and an enemy.
 * The hero can attack or attempt to flee each round.
 */
public final class CombatService {

    private final Dice fleeDice;

    public CombatService(Dice fleeDice) {
        this.fleeDice = Objects.requireNonNull(fleeDice);
    }

    public CombatOutcome fight(Scanner scanner, Hero hero, Enemy enemy) {
        Objects.requireNonNull(scanner);
        Objects.requireNonNull(hero);
        Objects.requireNonNull(enemy);

        System.out.println("Combat starts!");

        while (hero.getLifePoints() > 0 && enemy.getLifePoints() > 0) {
            printStatus(hero, enemy);

            int action = readCombatAction(scanner);

            if (action == 2) {
                int steps = fleeDice.roll();
                System.out.println("You fled!");
                return new CombatOutcome(CombatEndState.HERO_FLED, steps);
            }

            int heroDamage = hero.getTotalAttack();
            enemy.setLifePoints(Math.max(0, enemy.getLifePoints() - heroDamage));
            System.out.printf("You hit %s for %d damage. Enemy HP=%d%n", enemy.getName(), heroDamage, enemy.getLifePoints());

            if (enemy.getLifePoints() <= 0) {
                System.out.println("Enemy defeated!");
                return new CombatOutcome(CombatEndState.ENEMY_DEFEATED, 0);
            }

            int enemyDamage = enemy.getAttack();
            hero.setLifePoints(Math.max(0, hero.getLifePoints() - enemyDamage));
            System.out.printf("%s hits you for %d damage. Your HP=%d%n", enemy.getName(), enemyDamage, hero.getLifePoints());
        }

        return new CombatOutcome(CombatEndState.HERO_DIED, 0);
    }

    private void printStatus(Hero hero, Enemy enemy) {
        System.out.printf("Hero HP=%d | Enemy %s HP=%d%n", hero.getLifePoints(), enemy.getName(), enemy.getLifePoints());
        System.out.println("1) Attack");
        System.out.println("2) Flee");
    }

    private int readCombatAction(Scanner scanner) {
        while (true) {
            System.out.print("Your choice: ");
            String input = scanner.nextLine().trim();

            if ("1".equals(input) || "2".equals(input)) {
                return Integer.parseInt(input);
            }

            System.out.println("Invalid choice. Please enter 1 or 2.");
        }
    }
}
