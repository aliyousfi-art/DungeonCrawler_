package com.dnd.menu;

import com.dnd.board.Ennemi;
import com.dnd.board.InteractionResult;
import com.dnd.board.Potion;
import com.dnd.combat.CombatEndState;
import com.dnd.combat.CombatOutcome;
import com.dnd.combat.CombatService;
import com.dnd.db.HeroEntity;
import com.dnd.db.HeroMapper;
import com.dnd.db.HeroRepository;
import com.dnd.db.HeroSession;
import com.dnd.game.Game;
import com.dnd.game.PersonnageHorsPlateauException;
import com.dnd.game.RandomDice;
import com.dnd.game.TurnOutcome;
import com.dnd.model.CharacterType;
import com.dnd.model.character.Guerrier;
import com.dnd.model.character.Magicien;
import com.dnd.model.character.Personnage;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public final class Menu {

    private final HeroRepository heroRepository;
    private final HeroMapper heroMapper;

    public Menu(HeroRepository heroRepository) {
        this.heroRepository = Objects.requireNonNull(heroRepository);
        this.heroMapper = new HeroMapper();
    }

    public void run(Scanner scanner, Game game) {
        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = readInt(scanner, "Your choice: ");

            switch (choice) {
                case 1 -> playWithExistingHero(scanner, game);
                case 2 -> createHeroAndPlay(scanner, game);
                case 3 -> printBoard(game);
                case 4 -> running = false;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }

        System.out.println("Bye.");
    }

    private void playWithExistingHero(Scanner scanner, Game game) {
        List<HeroEntity> heroes;
        try {
            heroes = heroRepository.getHeroes();
        } catch (SQLException ex) {
            System.out.println("DB error while loading heroes: " + ex.getMessage());
            return;
        }

        if (heroes.isEmpty()) {
            System.out.println("No heroes in DB. Create one first.");
            return;
        }

        System.out.println("=== Heroes ===");
        for (int i = 0; i < heroes.size(); i++) {
            HeroEntity h = heroes.get(i);
            System.out.printf("%d) #%d %s (%s) PV=%d Force=%d%n",
                    i + 1,
                    h.id(),
                    h.name(),
                    h.type(),
                    h.lifePoints(),
                    h.baseAttack()
            );
        }

        int idx = readInt(scanner, "Choose hero number: ") - 1;
        if (idx < 0 || idx >= heroes.size()) {
            System.out.println("Invalid hero.");
            return;
        }

        HeroEntity selected = heroes.get(idx);
        Personnage hero = heroMapper.toDomain(selected);
        HeroSession session = new HeroSession(selected.id(), hero);

        manageHero(scanner, game, session);
    }

    private void createHeroAndPlay(Scanner scanner, Game game) {
        CharacterType type = readCharacterType(scanner);
        String name = readNonBlankString(scanner, "Name: ");

        Personnage hero = newPersonnage(type, name);

        try {
            HeroEntity created = heroRepository.createHero(heroMapper.toEntity(null, hero));
            System.out.println("Hero saved with id #" + created.id());
            manageHero(scanner, game, new HeroSession(created.id(), hero));
        } catch (SQLException ex) {
            System.out.println("DB error while creating hero: " + ex.getMessage());
        }
    }

    private void manageHero(Scanner scanner, Game game, HeroSession session) {
        HeroSession currentSession = session;

        boolean managing = true;
        while (managing) {
            Personnage hero = currentSession.hero();

            printHeroMenu(hero);
            int choice = readInt(scanner, "Your choice: ");

            switch (choice) {
                case 1 -> System.out.println(hero);
                case 2 -> currentSession = editHero(scanner, currentSession);
                case 3 -> playGame(scanner, game, currentSession);
                case 4 -> managing = false;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private HeroSession editHero(Scanner scanner, HeroSession session) {
        Personnage hero = session.hero();

        System.out.println("Edit hero");
        System.out.println("1) Change name");
        System.out.println("2) Change type (creates a new character with same name)");
        System.out.println("3) Back");

        int choice = readInt(scanner, "Your choice: ");

        Personnage updated;
        switch (choice) {
            case 1 -> {
                hero.setName(readNonBlankString(scanner, "New name: "));
                updated = hero;
            }
            case 2 -> {
                CharacterType newType = readCharacterType(scanner);
                updated = newPersonnage(newType, hero.getName());
            }
            case 3 -> {
                return session;
            }
            default -> {
                System.out.println("Invalid choice.");
                return session;
            }
        }

        try {
            heroRepository.editHero(heroMapper.toEntity(session.id(), updated));
            System.out.println("Hero saved.");
            return new HeroSession(session.id(), updated);
        } catch (SQLException ex) {
            System.out.println("DB error while editing hero: " + ex.getMessage());
            return session;
        }
    }

    private void playGame(Scanner scanner, Game game, HeroSession session) {
        Personnage hero = session.hero();
        CombatService combatService = new CombatService(new RandomDice());

        game.startNewGame(hero);

        System.out.printf("Starting game. You are on tile %d/%d.%n", game.getPlayerPosition(), game.getBoardSize());
        System.out.println("Current tile: " + game.getCurrentCase());

        while (game.isRunning()) {
            readLine(scanner, "Press ENTER to roll the dice...");

            try {
                TurnOutcome outcome = game.playOneTurn();

                System.out.printf("You rolled %d. You are now on tile %d/%d.%n",
                        outcome.roll(),
                        outcome.position(),
                        game.getBoardSize()
                );

                System.out.println("You landed on: " + outcome.landedCase());

                // Iteration 7 asks for an interaction() method on Case.
                InteractionResult interactionResult = outcome.landedCase().interaction(hero);

                if (outcome.landedCase() instanceof Potion) {
                    // Potion interaction updated hero PV.
                    persistLifePoints(session);
                }

                // Only remove the tile if it was actually consumed / taken.
                if (interactionResult == InteractionResult.REMOVE_TILE) {
                    game.clearCurrentCase();
                }

                if (outcome.landedCase() instanceof Ennemi enemy) {
                    CombatOutcome combatOutcome = combatService.fight(scanner, hero, enemy);
                    persistLifePoints(session);

                    if (combatOutcome.endState() == CombatEndState.ENEMY_DEFEATED) {
                        // Enemy disappears from the board when dead.
                        game.clearCurrentCase();
                    } else if (combatOutcome.endState() == CombatEndState.HERO_FLED) {
                        game.moveBack(combatOutcome.fleeSteps());
                        System.out.printf("You moved back %d tile(s). Now at %d/%d.%n",
                                combatOutcome.fleeSteps(),
                                game.getPlayerPosition(),
                                game.getBoardSize());
                        System.out.println("Current tile: " + game.getCurrentCase());
                    }

                    if (game.hasLost()) {
                        System.out.println("Your hero died. Game over.");
                    }
                }

            } catch (PersonnageHorsPlateauException ex) {
                System.out.printf("Roll would move you out of the board (%d -> %d). You stay on tile %d/%d.%n",
                        ex.getCurrentPosition(),
                        ex.getAttemptedPosition(),
                        ex.getCurrentPosition(),
                        ex.getBoardSize()
                );
            }
        }

        if (game.hasWon()) {
            System.out.println("You reached the end of the board. You win!");
        } else if (game.hasLost()) {
            System.out.println("You lost the game.");
        }

        game.endGame();

        System.out.println("1) Play again");
        System.out.println("2) Back to hero menu");

        int choice = readInt(scanner, "Your choice: ");
        if (choice == 1) {
            playGame(scanner, game, session);
        }
    }

    private void persistLifePoints(HeroSession session) {
        try {
            heroRepository.changeLifePoints(session.id(), session.hero().getLifePoints());
        } catch (SQLException ex) {
            System.out.println("DB error while saving life points: " + ex.getMessage());
        }
    }

    private void printMainMenu() {
        System.out.println("=== D&D (Iteration 7) ===");
        System.out.println("1) Choose existing hero (DB)");
        System.out.println("2) Create new hero (DB)");
        System.out.println("3) Print board (debug)");
        System.out.println("4) Quit");
    }

    private void printBoard(Game game) {
        System.out.println("=== Board ===");
        for (int pos = 1; pos <= game.getBoardSize(); pos++) {
            System.out.printf("%02d: %s%n", pos, game.getCaseAt(pos));
        }
    }

    private void printHeroMenu(Personnage hero) {
        System.out.println("=== Hero ===");
        System.out.println("Current: " + hero.getName() + " (" + hero.getType() + ")");
        System.out.println("1) Show hero info");
        System.out.println("2) Edit hero");
        System.out.println("3) Start game");
        System.out.println("4) Back to main menu");
    }

    private CharacterType readCharacterType(Scanner scanner) {
        while (true) {
            System.out.println("Choose type:");
            System.out.println("1) Warrior");
            System.out.println("2) Wizard");

            int choice = readInt(scanner, "Your choice: ");
            switch (choice) {
                case 1 -> {
                    return CharacterType.WARRIOR;
                }
                case 2 -> {
                    return CharacterType.WIZARD;
                }
                default -> System.out.println("Invalid type. Try again.");
            }
        }
    }

    private Personnage newPersonnage(CharacterType type, String name) {
        return switch (type) {
            case WARRIOR -> new Guerrier(name);
            case WIZARD -> new Magicien(name);
        };
    }

    private int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private String readNonBlankString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            if (!input.isBlank()) {
                return input;
            }

            System.out.println("Input must not be blank.");
        }
    }

    private void readLine(Scanner scanner, String prompt) {
        System.out.print(prompt);
        scanner.nextLine();
    }
}
