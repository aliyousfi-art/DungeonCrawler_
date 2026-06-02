package com.dnd.game;

/**
 * Abstraction for a six-sided die, allowing fixed or random implementations.
 */
public interface Dice {

    /**
     * Rolls the die.
     *
     * @return a value between 1 and 6 (inclusive)
     */
    int roll();
}
