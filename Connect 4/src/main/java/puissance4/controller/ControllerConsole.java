package puissance4.controller;

import puissance4.exception.ColumnFullException;
import puissance4.exception.NonExistentPositionException;
import puissance4.model.Model;
import puissance4.model.ModelConsole;
import puissance4.view.ViewConsole;

/**
 * This class contains all the methods to handle a Controller which controls all the game. It serves as a connexion
 * between the model and the view.
 */
public class ControllerConsole {

    private final Model modelConsole;
    private final ViewConsole viewConsole;

    /**
     * Constructor for the Controller
     */
    public ControllerConsole() {
        this.modelConsole = new ModelConsole();
        this.viewConsole = new ViewConsole(modelConsole);
    }

    /**
     * This method launches the game and controls. It contains the whole game loop.
     */
    public void play() {
        viewConsole.displayWelcome();
        modelConsole.start();
        while (modelConsole.play()) {
            try {
                viewConsole.displayTurn();
                viewConsole.displayContainer();
                modelConsole.placeACoin(viewConsole.askForColumn());
            } catch (NonExistentPositionException | ColumnFullException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        if (modelConsole.win()) {
            viewConsole.displayWinner();
        } else if (modelConsole.draw()) {
            viewConsole.displayDraw();
        }
    }
}
