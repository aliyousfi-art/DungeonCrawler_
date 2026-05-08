package com.dnd.board;

/** Strongest enemy on the board — high HP and attack, found in late zones. */
public final class Dragon extends Enemy {

    public Dragon() {
        super("Dragon", 15, 4);
    }

    @Override
    public String toString() {
        return "Dragon{HP=" + getLifePoints() + ", Attack=" + getAttack() + '}';
    }
}
