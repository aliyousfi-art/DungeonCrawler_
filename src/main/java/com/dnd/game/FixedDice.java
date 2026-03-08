package com.dnd.game;

public final class FixedDice implements Dice {

    private final int value;

    public FixedDice(int value) {
        if (value < 1 || value > 6) {
            throw new IllegalArgumentException("value must be between 1 and 6");
        }
        this.value = value;
    }

    @Override
    public int roll() {
        return value;
    }
}
