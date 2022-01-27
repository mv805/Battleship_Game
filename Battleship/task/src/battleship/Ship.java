package battleship;

import java.util.Arrays;

public class Ship {

    private final char[] shipStatus;
    private final String type;

    int[] posXYfirst = new int[2];
    int[] posXYlast = new int[2];
    int[][] posCoord;


    public Ship(String type, int length) {
        this.shipStatus = new char[length];
        this.type = type;
        Arrays.fill(shipStatus, BoardSymbol.SHIP_CELL.getSymbol());
    }

    public String getType() {
        return type;
    }

    public char[] getShipStatus() {
        return shipStatus;
    }

    void defineEndpoints(int[] intCoord) {

        posXYfirst[0]= intCoord[0];
        posXYfirst[1] = intCoord[1];
        posXYlast[0] = intCoord[2];
        posXYlast[1] = intCoord[3];
        setCoordinates();
    }


    private void setCoordinates() {
        posCoord = new int[shipStatus.length][];
    }
}
