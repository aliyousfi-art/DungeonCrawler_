package com.dnd.game;

import com.dnd.board.Case;
import com.dnd.board.CaseVide;
import com.dnd.board.Plateau;
import com.dnd.model.character.Personnage;

import java.util.Objects;

public final class Game {

    private final Dice dice;
    private final Plateau plateau;

    private Personnage player;
    private int playerPosition;

    public Game(Dice dice, Plateau plateau) {
        this.dice = Objects.requireNonNull(dice);
        this.plateau = Objects.requireNonNull(plateau);
    }

    public int getBoardSize() {
        return plateau.size();
    }

    public void startNewGame(Personnage player) {
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

    public Personnage getPlayer() {
        requireGameStarted();
        return player;
    }

    public Case getCurrentCase() {
        requireGameStarted();
        return plateau.getCaseAt(playerPosition);
    }

    public Case getCaseAt(int position) {
        return plateau.getCaseAt(position);
    }

    public void clearCurrentCase() {
        requireGameStarted();
        plateau.setCaseAt(playerPosition, new CaseVide());
    }

    public void moveBack(int steps) {
        requireGameStarted();
        if (steps < 1) {
            throw new IllegalArgumentException("steps must be >= 1");
        }
        playerPosition = Math.max(1, playerPosition - steps);
    }

    public TurnOutcome playOneTurn() throws PersonnageHorsPlateauException {
        requireGameStarted();

        int roll = dice.roll();
        int nextPosition = playerPosition + roll;

        if (nextPosition > getBoardSize()) {
            throw new PersonnageHorsPlateauException(playerPosition, nextPosition, getBoardSize());
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
