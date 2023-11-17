package puissance4.model;

public class Box {

    private Coin coin;

    public Box() {
        this.coin = null;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public boolean isFree() {
        return this.coin == null;
    }
}
