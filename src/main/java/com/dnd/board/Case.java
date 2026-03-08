package com.dnd.board;

import com.dnd.model.character.Personnage;

public interface Case {

    String describe();

    /**
     * Interaction between the player and this board tile.
     * The subject for iteration 7 asks for an interaction method that takes a {@link Personnage}.
     */
    default InteractionResult interaction(Personnage personnage) {
        // Default behavior: just describe the tile.
        System.out.println(describe());
        return InteractionResult.KEEP_TILE;
    }
}
