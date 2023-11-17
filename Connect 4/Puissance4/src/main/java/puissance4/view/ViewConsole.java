package puissance4.view;

import puissance4.exception.NonExistentPositionException;
import puissance4.model.Container;
import puissance4.model.Model;
import puissance4.model.Position;

import java.util.Scanner;

public class ViewConsole {

    private final Model modelConsole;

    private final String ANSI_RESET = "\u001B[0m";
    private final Scanner scanner = new Scanner(System.in);

    public ViewConsole(Model modelConsole) {
        this.modelConsole = modelConsole;
    }

    public int askForColumn() {
        String x = readEntry().replaceAll(" ", "");
        int column = Integer.parseInt(x);
        return column - 1;
    }

    public void displayContainer() {
        try {
            Container container = modelConsole.getContainer();
            for (int i = 0; i < container.getContainer().length; i++) {
                for (int j = 0; j < container.getContainer()[i].length; j++) {
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

    private String readEntry() {
        System.out.println("Enter a number between 1 and 7, both included.");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Entered message is not a number");
            System.out.println("Enter a number between 1 and 7, both included.");
        }
        return scanner.nextLine();
    }

    public void diplayWinner() {
        displayContainer();
        System.out.println("The winner is " + modelConsole.getCurrentPlayer().getColor().getInfo() + "Player" + ANSI_RESET + ".");
    }

    public void displayDraw() {
        displayContainer();
        System.out.println("It's a draw. Nobody wins");
    }

    public void displayWelcome() {
        System.out.println("Welcome to Connect 4");
        /*
         *System.out.println("This are the rule : \n" +
         "There is 2 player, Red and Yellow, and you, both, have the same goal, connect 4 of yours coins together.\n" +
         "For that you need to drop a coin of yours into the different columns. The First one to reach this goal, wins. \n" +
         "Good luck ! Have fun !");
         */
    }

    public void displayTurn(){
        System.out.println("It's the turn of " + modelConsole.getCurrentPlayer().getColor().getInfo() + "Player" + ANSI_RESET);
    }


}