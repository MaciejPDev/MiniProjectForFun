package puissance4.model;

/**
 * The color enumeration contains the two possible color for a coin
 */
public enum Color {
    BLUE("\u001B[34m"), YELLOW("\u001B[33m");


    private final String info;

    /**
     * Constructor for the Coin
     * @param info the ANSI information needed for the change of color in the console
     */
    Color(String info) {
        this.info = info;
    }

    /**
     * Getter for the info
     * @return the info which is a String
     */
    public String getInfo() {
        return info;
    }

    /**
     * Since there is only two colors, this method will return the opposite color
     * @return a color
     */
    public Color getOpposite() {
        if (this == BLUE) {
            return YELLOW;
        } else {
            return BLUE;
        }
    }
}
