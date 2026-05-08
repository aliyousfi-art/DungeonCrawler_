package com.dnd.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The game board, a fixed-size list of tiles indexed from position 1.
 */
public final class Board {

    private final List<Tile> tiles;

    public Board(List<Tile> tiles) {
        Objects.requireNonNull(tiles);
        if (tiles.isEmpty()) {
            throw new IllegalArgumentException("tiles must not be empty");
        }
        this.tiles = new ArrayList<>(tiles);
    }

    public int size() {
        return tiles.size();
    }

    public Tile getTileAt(int position) {
        validatePosition(position);
        return tiles.get(position - 1);
    }

    public void setTileAt(int position, Tile newTile) {
        validatePosition(position);
        tiles.set(position - 1, Objects.requireNonNull(newTile));
    }

    private void validatePosition(int position) {
        if (position < 1 || position > size()) {
            throw new IllegalArgumentException("position must be between 1 and " + size());
        }
    }

    public static Board of(Tile t1, Tile t2, Tile t3, Tile t4) {
        List<Tile> tiles = new ArrayList<>();
        tiles.add(Objects.requireNonNull(t1));
        tiles.add(Objects.requireNonNull(t2));
        tiles.add(Objects.requireNonNull(t3));
        tiles.add(Objects.requireNonNull(t4));
        return new Board(tiles);
    }
}
