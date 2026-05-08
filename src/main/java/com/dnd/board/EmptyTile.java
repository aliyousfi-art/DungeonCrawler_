package com.dnd.board;

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
