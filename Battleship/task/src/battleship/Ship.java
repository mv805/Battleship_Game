package battleship;

public class Ship {

    private final char[] shipStatus;
    private final String type;
    int[] posXYfirst;
    int[] posXYlast;


    public Ship(String type, int length) {
        this.shipStatus = new char[length];
        this.type = type;
    }
}
