package puissance4.controller;

import puissance4.exception.ColumnFullException;
import puissance4.exception.NonExistentPositionException;
import puissance4.model.Model;
import puissance4.model.ModelConsole;
import puissance4.view.ViewConsole;

public class ControllerConsole {

    private Model modelConsole;
    private ViewConsole viewConsole;

    public ControllerConsole() {
        this.modelConsole = new ModelConsole();
        this.viewConsole = new ViewConsole(modelConsole);
    }

    public void play() {
        viewConsole.displayWelcome();
        modelConsole.start();
        while (modelConsole.play()) {
            try {
                viewConsole.displayTurn();
                viewConsole.displayContainer();
                modelConsole.placeACoin(viewConsole.askForColumn());
            } catch (NonExistentPositionException | ColumnFullException e) {
                System.out.println(e.getMessage());
            }
        }
        if (modelConsole.win()){
            viewConsole.diplayWinner();
        } else if (modelConsole.draw()){
            viewConsole.displayDraw();
        }
    }
}
