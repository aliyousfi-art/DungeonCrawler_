package com.dnd;

import com.dnd.board.Board;
import com.dnd.board.EmptyTile;
import com.dnd.board.Enemy;
import com.dnd.board.Potion;
import com.dnd.db.ConnectionProvider;
import com.dnd.db.DbConfig;
import com.dnd.db.DbConfigLoader;
import com.dnd.db.HeroRepository;
import com.dnd.db.JdbcHeroRepository;
import com.dnd.game.FixedDice;
import com.dnd.game.Game;
import com.dnd.menu.Menu;
import com.dnd.model.equipment.Weapon;

import java.util.Scanner;

/**
 * Application entry point for the DungeonCrawler game.
 * Wires up the board, dice, database access and launches the interactive menu.
 */
public final class Main {

    /**
     * Bootstraps the game and runs the main menu loop.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Board board = Board.demoBoard4Tiles(
                new EmptyTile(),
                new Enemy("Goblin", 6, 2),
                new Weapon("Club", 3),
                new Potion("Health Potion", 2)
        );

        Game game = new Game(new FixedDice(1), board);

        try {
            DbConfig config = new DbConfigLoader("db/db.properties").load();
            HeroRepository heroRepository = new JdbcHeroRepository(new ConnectionProvider(config));
            new Menu(heroRepository).run(scanner, game);
        } catch (RuntimeException ex) {
            System.out.println("Unable to start (DB config missing/invalid).\n" +
                    "Create src/main/resources/db/db.properties from db/db.properties.example.\n" +
                    "Details: " + ex.getMessage());
        }
    }
}
