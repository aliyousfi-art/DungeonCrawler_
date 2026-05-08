package com.dnd.board;

/** Weak enemy, low HP and attack. Common early-game obstacle. */
public final class Goblin extends Enemy {

    public Goblin() {
        super("Goblin", 6, 2);
    }

    @Override
    public String toString() {
        return "Goblin{HP=" + getLifePoints() + ", Attack=" + getAttack() + '}';
    }
}
