package battleship;

import java.util.Arrays;

public class Ship {

    private final char[] shipStatus;//X O characters for visual
    private final String type;
    private final int x1 = 0;
    private final int y1 = 1;
    private final int x2 = 2;
    private final int y2 = 3;

    //int[] posXYfirst = new int[2];
    //int[] posXYlast = new int[2];
    int[][] posCoord;
    int[][] boardSim = new int[10][10];


    public Ship(String type, int length) {
        this.shipStatus = new char[length];
        this.type = type;
        this.posCoord = new int[length][2];
        Arrays.fill(shipStatus, BoardSymbol.SHIP_CELL.getSymbol());
    }

    public String getType() {
        return type;
    }

    public char[] getShipStatus() {
        return shipStatus;
    }

    void recordShipCoord(int[] intCoord){
        /*
            array

             i |
               V                 x y coordinates
            [0, 0] [0, 1] <-j    (5) (3) x1, y1
            [1, 0] [1, 1]        (5) (4)
            [2, 0] [2, 1]        (5) (5)
            [3, 0] [3, 1]        (5) (6)
            [4, 0] [4, 1]        (5) (7) x2, y2
        */
        //if horizontal left to right
        if (intCoord[x1] == intCoord[x2] && intCoord[y1] < intCoord[y2]){
            fillLeftToRight(intCoord[x1],intCoord[y1]);
        //if horizontal right to left
        } else if (intCoord[x1] == intCoord[x2] && intCoord[y1] > intCoord[y2]){
            fillLeftToRight(intCoord[x2],intCoord[y2]);
        }

    }

    void fillLeftToRight(int x, int y){

        for (int i = 0; i < posCoord.length; i++){
            posCoord[i][0] = x;
            posCoord[i][1] = y + i;
        }

    }
}
