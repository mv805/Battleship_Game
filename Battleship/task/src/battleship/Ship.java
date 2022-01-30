package battleship;

public class Ship {

    private final String type;
    private final int shipLength;
    int shipHits = 0;

    private int[][] posCoord;

    public Ship(String type, int length) {

        this.type = type;
        this.posCoord = new int[length][2];
        this.shipLength = length;

    }

    public String getType() {
        return type;
    }
    public int getShipLength() { return shipLength; }
    public int[][] getPosCoord(){return posCoord;}
    public void setPosCoord(int[] intCoord){
        //record the coordinates of the ship from two input points supplied
        //in an array (x1, y1, x2, y2)
        posCoord =
            CoordinateTool.returnCoordinate(intCoord, posCoord);}
}
