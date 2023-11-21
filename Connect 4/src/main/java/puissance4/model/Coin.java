package puissance4.model;

/**
 * This class contains all the methods to handle a Coin
 */
public class Coin {

    private Color color;

    /**
     * Constructor for the Coin
     *
     * @param color the color of the coin
     */
    public Coin(Color color) {
        this.color = color;
    }

    /**
     * Getter for the color of the coin
     *
     * @return the color of the coin
     */
    public Color getColor() {
        return color;
    }
}
