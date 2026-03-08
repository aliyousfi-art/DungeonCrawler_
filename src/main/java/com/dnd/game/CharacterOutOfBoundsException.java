package com.dnd.game;

/**
 * Thrown when a dice roll would move the player past the end of the board.
 */
public final class CharacterOutOfBoundsException extends Exception {

    private final int currentPosition;
    private final int attemptedPosition;
    private final int boardSize;

    /**
     * @param currentPosition   the player's current position
     * @param attemptedPosition the position the roll would have reached
     * @param boardSize         the size of the board
     */
    public CharacterOutOfBoundsException(int currentPosition, int attemptedPosition, int boardSize) {
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
