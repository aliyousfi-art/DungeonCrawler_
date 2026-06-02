package com.dnd.board;

/**
 * A single tile on the board. Anything that can be encountered while moving
 * (empty tile, enemy, potion, weapon) implements this interface.
 */
public interface Tile {

    /**
     * @return a human-readable description of this tile
     */
    String describe();
}
