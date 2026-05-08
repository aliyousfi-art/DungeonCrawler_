package com.dnd.board;

import com.dnd.model.character.Hero;

/**
 * A tile on the game board. Each tile can describe itself and trigger
 * an interaction when the hero lands on it.
 */
public interface Tile {

    String describe();

    default InteractionResult interaction(Hero hero) {
        System.out.println(describe());
        return InteractionResult.KEEP_TILE;
    }
}
