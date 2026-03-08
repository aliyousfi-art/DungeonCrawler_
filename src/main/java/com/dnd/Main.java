package com.dnd;

import com.dnd.board.Plateau;
import com.dnd.board.factory.PlateauFactory;
import com.dnd.db.ConnectionProvider;
import com.dnd.db.DbConfig;
import com.dnd.db.DbConfigLoader;
import com.dnd.db.HeroRepository;
import com.dnd.db.JdbcHeroRepository;
import com.dnd.game.Game;
import com.dnd.game.RandomDice;
import com.dnd.menu.Menu;

import java.util.Random;
import java.util.Scanner;

public final class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean randomBoard = Boolean.parseBoolean(System.getProperty("board.random", "false"));
        Plateau plateau = randomBoard
                ? PlateauFactory.createRandom64(new Random())
                : PlateauFactory.createFixed64();

        Game game = new Game(new RandomDice(), plateau);

        try {
            DbConfig config = new DbConfigLoader("db/db.properties").load();
            HeroRepository heroRepository = new JdbcHeroRepository(new ConnectionProvider(config));
            new Menu(heroRepository).run(scanner, game);
        } catch (RuntimeException ex) {
            System.out.println("Unable to start (DB config missing/invalid).\n" +
                    "Create src/main/resources/db/db.properties from db/db.properties.example (or set DB_URL/DB_USER/DB_PASSWORD env vars).\n" +
                    "Details: " + ex.getMessage());
        }
    }
}
