package puissance4.model;

public enum Color {
    BLUE("\u001B[34m"), YELLOW("\u001B[33m");


    private final String info;

    Color(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public Color getOpposite() {
        if (this == BLUE) {
            return YELLOW;
        } else {
            return BLUE;
        }
    }
}
