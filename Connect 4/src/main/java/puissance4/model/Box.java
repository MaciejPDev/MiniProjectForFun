package puissance4.model;

/**
 * This class contains the methods to handle a Box which will contain a Coin
 */
public class Box {

    private Coin coin;

    /**
     * Constructor for the Box
     */
    public Box() {
        this.coin = null;
    }

    /**
     * Getter for the coin attribut
     *
     * @return a coin
     */
    public Coin getCoin() {
        return coin;
    }

    /**
     * Setter for the coin attribut
     *
     * @param coin a coin which will be placed in the Box
     */
    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    /**
     * This method verify is the box contains a coin or not
     *
     * @return true if the box does not contain a coin, false otherwise
     */
    public boolean isFree() {
        return this.coin == null;
    }
}
