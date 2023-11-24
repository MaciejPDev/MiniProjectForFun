package puissance4.view;

import puissance4.exception.NonExistentPositionException;
import puissance4.model.Container;
import puissance4.model.Model;
import puissance4.model.Position;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class contains all the methods to handle a ViewConsole
 */
public class ViewConsole {

    private final Model modelConsole;

    private final Pattern pattern = Pattern.compile("[0-9]+");

    private final String ANSI_RESET = "\u001B[0m";
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructor for the viewConsole
     *
     * @param modelConsole the model of the game
     */
    public ViewConsole(Model modelConsole) {
        this.modelConsole = modelConsole;
    }

    /**
     * Returns an int obtains from a string. This int will be the number of the column in which the player want to place
     * a coin.
     *
     * @return an int
     */
    public int askForColumn() {
        return Integer.parseInt(readEntry()) - 1;
    }

    /**
     * Displays the container
     */
    public void displayContainer() {
        try {
            Container container = modelConsole.getContainer();
            for (int i = 0; i < container.getHeight(); i++) {
                for (int j = 0; j < container.getWidth(); j++) {
                    if (!container.isFree(new Position(i, j))) {
                        System.out.print("|" + container.getCoin(new Position(i, j)).getColor().getInfo() + " X " + ANSI_RESET);
                    } else {
                        System.out.print("|   ");
                    }
                }
                System.out.println("|");
            }
        } catch (NonExistentPositionException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads the message by entered the player and verifies if the program can work with it
     *
     * @return a string
     */
    private String readEntry() {
        System.out.println("Enter a number between 1 and 7, both included.");
        String x = scanner.nextLine();
        if (pattern.matcher(x).find()) {
            return x;
        } else {
            throw new IllegalArgumentException("Entered message is not a number");
        }
    }

    /**
     * Displays the winner
     */
    public void displayWinner() {
        displayContainer();
        System.out.println("The winner is " + modelConsole.getCurrentPlayer().getColor().getInfo() + "Player" + ANSI_RESET + ".");
    }

    /**
     * Displays a message to inform that the game's result is a draw
     */
    public void displayDraw() {
        displayContainer();
        System.out.println("It's a draw. Nobody wins");
    }

    /**
     * Displays a welcome message and the rules of the game
     */
    public void displayWelcome() {
        System.out.println("Welcome to Connect 4");
        System.out.println("This are the rule : \n" +
                "There are 2 player, Blue and Yellow, and you, both, have the same goal, connect 4 of yours coins together.\n" +
                "For that you need to drop a coin of yours into the different columns. The First one to reach this goal, wins. \n" +
                "Good luck ! Have fun !");
    }

    /**
     * Informs the players who's turn it is
     */
    public void displayTurn() {
        System.out.println("It's the turn of " + modelConsole.getCurrentPlayer().getColor().getInfo() + "Player" + ANSI_RESET);
    }


}
