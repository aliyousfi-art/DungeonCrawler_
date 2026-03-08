package com.dnd.board;

public final class Sorcier extends Ennemi {

    public Sorcier() {
        super("Sorcier", 9, 3);
    }

    @Override
    public String toString() {
        return "Sorcier{PV=" + getLifePoints() + ", Attaque=" + getAttack() + '}';
    }
}
