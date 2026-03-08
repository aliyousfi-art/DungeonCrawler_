package com.dnd.board;

/**
 * An empty board tile with no effect.
 */
public final class EmptyTile implements Tile {

    @Override
    public String describe() {
        return "Empty tile";
    }

    @Override
    public String toString() {
        return describe();
    }
}
