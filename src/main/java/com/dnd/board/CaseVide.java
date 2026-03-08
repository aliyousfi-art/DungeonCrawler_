package com.dnd.board;

public final class CaseVide implements Case {

    @Override
    public String describe() {
        return "Case vide";
    }

    @Override
    public String toString() {
        return describe();
    }
}
