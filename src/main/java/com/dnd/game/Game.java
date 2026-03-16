package com.dnd.game;

import com.dnd.board.Board;
import com.dnd.board.Tile;
import com.dnd.model.character.Hero;

import java.util.Objects;

/**
 * Main game controller for the dungeon crawler.
 * Manages game state, player position, and turn-based movement.
 */
public final class Game {

    private final Dice dice;
    private final Board board;

    private Hero player;
    private int playerPosition;

    /**
     * Creates a new game with the specified dice and board.
     *
     * @param dice  the dice used for rolling
     * @param board the game board
     */
    public Game(Dice dice, Board board) {
        this.dice = Objects.requireNonNull(dice);
        this.board = Objects.requireNonNull(board);
    }

    /**
     * Gets the size of the game board.
     *
     * @return the board size
     */
    public int getBoardSize() {
        return board.size();
    }

    /**
     * Starts a new game with the specified player.
     *
     * @param player the player character
     */
    public void startNewGame(Hero player) {
        this.player = Objects.requireNonNull(player);
        this.playerPosition = 1;
    }

    /**
     * Checks if the game is currently running.
     *
     * @return true if game is active, false otherwise
     */
    public boolean isRunning() {
        return player != null && playerPosition < getBoardSize();
    }

    /**
     * Gets the current player position.
     *
     * @return the position on the board
     */
    public int getPlayerPosition() {
        return playerPosition;
    }

    /**
     * Gets the board tile at the current player position.
     *
     * @return the current tile
     * @throws IllegalStateException if game hasn't started
     */
    public Tile getCurrentTile() {
        requireGameStarted();
        return board.getTileAt(playerPosition);
    }

    /**
     * Plays one turn: rolls dice and moves player.
     *
     * @return the outcome of the turn
     * @throws CharacterOutOfBoundsException if roll exceeds board size
     * @throws IllegalStateException         if game hasn't started
     */
    public TurnOutcome playOneTurn() throws CharacterOutOfBoundsException {
        requireGameStarted();

        int roll = dice.roll();
        int nextPosition = playerPosition + roll;

        if (nextPosition > getBoardSize()) {
            throw new CharacterOutOfBoundsException(playerPosition, nextPosition, getBoardSize());
        }

        playerPosition = nextPosition;
        return new TurnOutcome(roll, playerPosition, getCurrentTile());
    }

    /**
     * Checks if the player has reached the goal.
     *
     * @return true if player has won, false otherwise
     * @throws IllegalStateException if game hasn't started
     */
    public boolean hasWon() {
        requireGameStarted();
        return playerPosition >= getBoardSize();
    }

    /**
     * Ends the current game.
     */
    public void endGame() {
        player = null;
        playerPosition = 0;
    }

    private void requireGameStarted() {
        if (player == null) throw new IllegalStateException("Game not started");
    }
}
