package com.dnd.board;

public final class Dragon extends Enemy {

    public Dragon() {
        super("Dragon", 15, 4);
    }

    @Override
    public String toString() {
        return "Dragon{HP=" + getLifePoints() + ", Attack=" + getAttack() + '}';
    }
}
