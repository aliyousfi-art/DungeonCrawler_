package com.dnd.board.factory;

import com.dnd.board.Board;
import com.dnd.board.Dragon;
import com.dnd.board.EmptyTile;
import com.dnd.board.Goblin;
import com.dnd.board.LargePotion;
import com.dnd.board.Sorcerer;
import com.dnd.board.StandardPotion;
import com.dnd.board.Tile;
import com.dnd.model.equipment.Club;
import com.dnd.model.equipment.Fireball;
import com.dnd.model.equipment.Lightning;
import com.dnd.model.equipment.Sword;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Creates pre-configured or randomised 64-tile game boards.
 */
public final class BoardFactory {

    private BoardFactory() {
    }

    public static Board createFixed64() {
        List<Tile> tiles = new ArrayList<>(Collections.nCopies(64, new EmptyTile()));

        // Enemies
        put(tiles, 45, new Dragon());
        put(tiles, 52, new Dragon());
        put(tiles, 56, new Dragon());
        put(tiles, 62, new Dragon());

        put(tiles, 10, new Sorcerer());
        put(tiles, 20, new Sorcerer());
        put(tiles, 25, new Sorcerer());
        put(tiles, 32, new Sorcerer());
        put(tiles, 35, new Sorcerer());
        put(tiles, 36, new Sorcerer());
        put(tiles, 37, new Sorcerer());
        put(tiles, 40, new Sorcerer());
        put(tiles, 44, new Sorcerer());
        put(tiles, 47, new Sorcerer());

        put(tiles, 3, new Goblin());
        put(tiles, 6, new Goblin());
        put(tiles, 9, new Goblin());
        put(tiles, 12, new Goblin());
        put(tiles, 15, new Goblin());
        put(tiles, 18, new Goblin());
        put(tiles, 21, new Goblin());
        put(tiles, 24, new Goblin());
        put(tiles, 27, new Goblin());
        put(tiles, 30, new Goblin());

        // Items
        put(tiles, 2, new Club());
        put(tiles, 5, new Club());
        put(tiles, 11, new Club());
        put(tiles, 22, new Club());
        put(tiles, 38, new Club());

        put(tiles, 19, new Sword());
        put(tiles, 26, new Sword());
        put(tiles, 42, new Sword());
        put(tiles, 53, new Sword());

        put(tiles, 1, new Lightning());
        put(tiles, 4, new Lightning());
        put(tiles, 8, new Lightning());
        put(tiles, 17, new Lightning());
        put(tiles, 23, new Lightning());

        put(tiles, 48, new Fireball());
        put(tiles, 49, new Fireball());

        put(tiles, 7, new StandardPotion());
        put(tiles, 13, new StandardPotion());
        put(tiles, 31, new StandardPotion());
        put(tiles, 33, new StandardPotion());
        put(tiles, 39, new StandardPotion());
        put(tiles, 43, new StandardPotion());

        put(tiles, 28, new LargePotion());
        put(tiles, 41, new LargePotion());

        return new Board(tiles);
    }

    public static Board createRandom64(Random random) {
        List<Tile> tiles = new ArrayList<>(Collections.nCopies(64, new EmptyTile()));

        List<Integer> positions = new ArrayList<>();
        for (int i = 1; i <= 64; i++) {
            positions.add(i);
        }
        Collections.shuffle(positions, random);

        int idx = 0;

        idx = placeMany(tiles, positions, idx, 4, Dragon::new);
        idx = placeMany(tiles, positions, idx, 10, Sorcerer::new);
        idx = placeMany(tiles, positions, idx, 10, Goblin::new);

        idx = placeMany(tiles, positions, idx, 5, Club::new);
        idx = placeMany(tiles, positions, idx, 4, Sword::new);
        idx = placeMany(tiles, positions, idx, 5, Lightning::new);
        idx = placeMany(tiles, positions, idx, 2, Fireball::new);
        idx = placeMany(tiles, positions, idx, 6, StandardPotion::new);
        placeMany(tiles, positions, idx, 2, LargePotion::new);

        return new Board(tiles);
    }

    private static void put(List<Tile> tiles, int position, Tile tile) {
        tiles.set(position - 1, tile);
    }

    private static int placeMany(
            List<Tile> tiles,
            List<Integer> positions,
            int startIdx,
            int count,
            Supplier<? extends Tile> supplier
    ) {
        int idx = startIdx;
        for (int i = 0; i < count; i++) {
            int pos = positions.get(idx++);
            tiles.set(pos - 1, supplier.get());
        }
        return idx;
    }
}
