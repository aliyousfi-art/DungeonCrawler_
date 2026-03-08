package com.dnd.game;

import java.util.Random;

/**
 * A die backed by {@link java.util.Random}, returning a value between 1 and 6.
 */
public final class RandomDice implements Dice {

    private final Random random;

    /**
     * Creates a die using a default random source.
     */
    public RandomDice() {
        this(new Random());
    }

    /**
     * Creates a die using the supplied random source.
     *
     * @param random the random number generator to use
     */
    public RandomDice(Random random) {
        this.random = random;
    }

    @Override
    public int roll() {
        return random.nextInt(6) + 1;
    }
}
