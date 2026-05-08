package com.dnd.board;

/** An empty tile — no interaction, nothing happens when landing on it. */
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
