package com.dnd.board;

public final class Dragon extends Ennemi {

    public Dragon() {
        super("Dragon", 15, 4);
    }

    @Override
    public String toString() {
        return "Dragon{PV=" + getLifePoints() + ", Attaque=" + getAttack() + '}';
    }
}
