package com.dnd.game;

import com.dnd.board.Tile;

public record TurnOutcome(int roll, int position, Tile landedCase) {
}
