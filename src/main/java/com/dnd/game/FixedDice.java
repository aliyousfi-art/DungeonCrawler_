package com.dnd.game;

/**
 * A deterministic die that always returns the same value. Useful for testing.
 */
public final class FixedDice implements Dice {

    private final int value;

    /**
     * @param value the constant roll value (must be between 1 and 6)
     */
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
