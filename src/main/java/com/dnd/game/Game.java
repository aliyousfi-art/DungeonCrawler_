package com.dnd.game;

import com.dnd.board.Board;
import com.dnd.board.EmptyTile;
import com.dnd.board.Tile;
import com.dnd.model.character.Hero;

import java.util.Objects;

/**
 * Core game engine. Manages the player's position on the board,
 * dice rolls and win/lose conditions.
 */
public final class Game {

    private final Dice dice;
    private final Board board;

    private Hero player;
    private int playerPosition;

    public Game(Dice dice, Board board) {
        this.dice = Objects.requireNonNull(dice);
        this.board = Objects.requireNonNull(board);
    }

    public int getBoardSize() {
        return board.size();
    }

    public void startNewGame(Hero player) {
        this.player = Objects.requireNonNull(player);
        this.playerPosition = 1;
    }

    public boolean isRunning() {
        return player != null && !hasLost() && playerPosition < getBoardSize();
    }

    public boolean hasLost() {
        requireGameStarted();
        return player.getLifePoints() <= 0;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public Hero getPlayer() {
        requireGameStarted();
        return player;
    }

    public Tile getCurrentCase() {
        requireGameStarted();
        return board.getTileAt(playerPosition);
    }

    public Tile getCaseAt(int position) {
        return board.getTileAt(position);
    }

    public void clearCurrentCase() {
        requireGameStarted();
        board.setTileAt(playerPosition, new EmptyTile());
    }

    public void moveBack(int steps) {
        requireGameStarted();
        if (steps < 1) {
            throw new IllegalArgumentException("steps must be >= 1");
        }
        playerPosition = Math.max(1, playerPosition - steps);
    }

    public TurnOutcome playOneTurn() throws OutOfBoardException {
        requireGameStarted();

        int roll = dice.roll();
        int nextPosition = playerPosition + roll;

        if (nextPosition > getBoardSize()) {
            throw new OutOfBoardException(playerPosition, nextPosition, getBoardSize());
        }

        playerPosition = nextPosition;
        return new TurnOutcome(roll, playerPosition, getCurrentCase());
    }

    public boolean hasWon() {
        requireGameStarted();
        return playerPosition >= getBoardSize() && !hasLost();
    }

    public void endGame() {
        player = null;
        playerPosition = 0;
    }

    private void requireGameStarted() {
        if (player == null) {
            throw new IllegalStateException("Game not started");
        }
    }
}
