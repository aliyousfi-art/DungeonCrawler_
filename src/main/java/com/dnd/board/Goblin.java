package com.dnd.board;

public final class Goblin extends Enemy {

    public Goblin() {
        super("Goblin", 6, 2);
    }

    @Override
    public String toString() {
        return "Goblin{HP=" + getLifePoints() + ", Attack=" + getAttack() + '}';
    }
}
