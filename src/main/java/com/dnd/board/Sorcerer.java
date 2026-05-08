package com.dnd.board;

/** Mid-tier enemy with moderate HP and attack. */
public final class Sorcerer extends Enemy {

    public Sorcerer() {
        super("Sorcerer", 9, 3);
    }

    @Override
    public String toString() {
        return "Sorcerer{HP=" + getLifePoints() + ", Attack=" + getAttack() + '}';
    }
}
