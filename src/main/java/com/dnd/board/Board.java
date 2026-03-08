package com.dnd.board;

import java.util.List;
import java.util.Objects;

/**
 * The game board containing a sequence of tiles.
 * Each tile can be an empty tile, a potion, an enemy, or a weapon.
 */
public final class Board {

    private final List<Tile> tiles;

    /**
     * Creates a new board with the specified tiles.
     *
     * @param tiles the list of tiles on the board
     */
    public Board(List<Tile> tiles) {
        this.tiles = List.copyOf(Objects.requireNonNull(tiles));
    }

    /**
     * Builds a simplified demo board made of exactly four ordered tiles.
     *
     * @param first  the tile at position 1
     * @param second the tile at position 2
     * @param third  the tile at position 3
     * @param fourth the tile at position 4
     * @return a new four-tile board
     */
    public static Board demoBoard4Tiles(Tile first, Tile second, Tile third, Tile fourth) {
        return new Board(List.of(first, second, third, fourth));
    }

    /**
     * Gets the number of tiles on the board.
     *
     * @return the board size
     */
    public int size() {
        return tiles.size();
    }

    /**
     * Gets the tile at the specified position.
     *
     * @param position the position (1-based)
     * @return the tile at that position
     * @throws IndexOutOfBoundsException if position is invalid
     */
    public Tile getTileAt(int position) {
        return tiles.get(position - 1);
    }

    @Override
    public String toString() {
        return "Board: " + tiles;
    }
}
