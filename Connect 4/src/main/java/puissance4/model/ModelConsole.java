package puissance4.model;

import puissance4.exception.ColumnFullException;
import puissance4.exception.NonExistentPositionException;

/**
 * This class implements the Model interface and so, it contains all the methods to handle a ModelConsole.
 */
public class ModelConsole implements Model {

    private final Container container;
    private State state;
    private Player currentPlayer;
    private final Player yellowPlayer;
    private final Player bluePlayer;

    /**
     * Constructor for the ModelConsole
     */
    public ModelConsole() {
        this.yellowPlayer = new Player(Color.YELLOW);
        this.bluePlayer = new Player(Color.BLUE);
        this.container = new Container();
    }

    @Override
    public void start() {
        this.currentPlayer = bluePlayer;
        this.state = State.PLAY;
    }

    @Override
    public Container getContainer() {
        return new Container(container);
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void placeACoin(int column) throws ColumnFullException, NonExistentPositionException {
        this.container.placeACoin(column, this.currentPlayer.getColor());
        this.stateChanger();
        if (play()) {
            if (this.currentPlayer.getColor() == Color.YELLOW) {
                this.currentPlayer = bluePlayer;
            } else {
                this.currentPlayer = yellowPlayer;
            }
        }
    }

    @Override
    public boolean play() {
        return this.state == State.PLAY;
    }

    @Override
    public boolean win() {
        return this.state == State.WIN;
    }

    @Override
    public boolean draw() {
        return this.state == State.DRAW;
    }

    /**
     * This method is called when there is a possibility that the state game has changed.
     * It will update the state attribute.
     */
    private void stateChanger() {
        if (this.container.isWin()) {
            state = State.WIN;
        } else if (this.container.isDraw()) {
            state = State.DRAW;
        }
    }
}
