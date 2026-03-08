package com.dnd.board;

public final class Gobelin extends Ennemi {

    public Gobelin() {
        super("Gobelin", 6, 2);
    }

    @Override
    public String toString() {
        return "Gobelin{PV=" + getLifePoints() + ", Attaque=" + getAttack() + '}';
    }
}
