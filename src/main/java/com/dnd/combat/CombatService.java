package com.dnd.combat;

import com.dnd.board.Ennemi;
import com.dnd.game.Dice;
import com.dnd.model.character.Personnage;

import java.util.Objects;
import java.util.Scanner;

public final class CombatService {

    private final Dice fleeDice;

    public CombatService(Dice fleeDice) {
        this.fleeDice = Objects.requireNonNull(fleeDice);
    }

    public CombatOutcome fight(Scanner scanner, Personnage hero, Ennemi enemy) {
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

            // Hero attacks first
            int heroDamage = hero.getTotalAttack();
            enemy.setLifePoints(Math.max(0, enemy.getLifePoints() - heroDamage));
            System.out.printf("You hit %s for %d damage. Enemy PV=%d%n", enemy.getName(), heroDamage, enemy.getLifePoints());

            if (enemy.getLifePoints() <= 0) {
                System.out.println("Enemy defeated!");
                return new CombatOutcome(CombatEndState.ENEMY_DEFEATED, 0);
            }

            // Enemy retaliates
            int enemyDamage = enemy.getAttack();
            hero.setLifePoints(Math.max(0, hero.getLifePoints() - enemyDamage));
            System.out.printf("%s hits you for %d damage. Your PV=%d%n", enemy.getName(), enemyDamage, hero.getLifePoints());
        }

        return new CombatOutcome(CombatEndState.HERO_DIED, 0);
    }

    private void printStatus(Personnage hero, Ennemi enemy) {
        System.out.printf("Hero PV=%d | Enemy %s PV=%d%n", hero.getLifePoints(), enemy.getName(), enemy.getLifePoints());
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
