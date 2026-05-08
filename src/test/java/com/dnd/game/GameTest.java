package com.dnd.game;

import com.dnd.board.Board;
import com.dnd.board.EmptyTile;
import com.dnd.model.character.Warrior;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Board smallBoard() {
        return new Board(List.of(
                new EmptyTile(),
                new EmptyTile(),
                new EmptyTile(),
                new EmptyTile()
        ));
    }

    @Test
    void player_moves_forward_by_dice_roll() throws OutOfBoardException {
        var game = new Game(new FixedDice(2), smallBoard());
        game.startNewGame(new Warrior("Test"));

        TurnOutcome outcome = game.playOneTurn();

        assertEquals(2, outcome.roll());
        assertEquals(3, outcome.position());
    }

    @Test
    void game_throws_when_roll_exceeds_board() {
        var game = new Game(new FixedDice(6), smallBoard());
        game.startNewGame(new Warrior("Test"));

        assertThrows(OutOfBoardException.class, game::playOneTurn);
    }

    @Test
    void game_is_won_at_last_tile() throws OutOfBoardException {
        var game = new Game(new FixedDice(3), smallBoard());
        game.startNewGame(new Warrior("Test"));

        game.playOneTurn();

        assertTrue(game.hasWon());
    }

    @Test
    void hero_death_stops_game() {
        var game = new Game(new FixedDice(1), smallBoard());
        var hero = new Warrior("Dead");
        game.startNewGame(hero);
        hero.setLifePoints(0);

        assertFalse(game.isRunning());
        assertTrue(game.hasLost());
    }
}
