package puissance4.model;

/**
 * This class contains all the methods to handle a Player
 */
public class Player {

    private final Color color;

    /**
     * Constructor for the Player
     * @param color
     */
    public Player(Color color) {
        this.color = color;
    }

    /**
     * Getter for the Color attribute
     * @return the color of the Player
     */
    public Color getColor() {
        return color;
    }
}
