package com.dnd.game;

import com.dnd.board.Case;

public record TurnOutcome(int roll, int position, Case landedCase) {
}
