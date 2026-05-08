package com.dnd.board;

import com.dnd.model.character.Hero;

public interface Tile {

    String describe();

    default InteractionResult interaction(Hero hero) {
        System.out.println(describe());
        return InteractionResult.KEEP_TILE;
    }
}
