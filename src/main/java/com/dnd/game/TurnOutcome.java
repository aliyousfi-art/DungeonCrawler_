package com.dnd.game;

import com.dnd.board.Tile;

/**
 * Immutable result of a single turn.
 *
 * @param roll        the value rolled on the die
 * @param position    the resulting player position on the board
 * @param landedTile  the tile the player landed on
 */
public record TurnOutcome(int roll, int position, Tile landedTile) {
}
