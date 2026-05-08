package com.dnd.game;

/**
 * Thrown when a dice roll would move the player beyond the last tile.
 * The player stays in place when this happens.
 */
public final class OutOfBoardException extends Exception {

    private final int currentPosition;
    private final int attemptedPosition;
    private final int boardSize;

    public OutOfBoardException(int currentPosition, int attemptedPosition, int boardSize) {
        super("Position out of board: " + currentPosition + " -> " + attemptedPosition + " (max=" + boardSize + ")");
        this.currentPosition = currentPosition;
        this.attemptedPosition = attemptedPosition;
        this.boardSize = boardSize;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public int getAttemptedPosition() {
        return attemptedPosition;
    }

    public int getBoardSize() {
        return boardSize;
    }
}
