package battleship;

public class Ship {

    private final String type;
    private final int shipLength;
    boolean shipSunk = false;
    private int shipStrength;

    private int[][] posCoord;

    public Ship(String type, int length) {

        this.type = type;
        this.posCoord = new int[length][2];
        this.shipLength = length;
        this.shipStrength = shipLength;

    }

    public String getType() {
        return type;
    }

    public int getShipLength() { return shipLength; }

    public int[][] getPosCoord(){return posCoord;}

    public int getShipStrength() {
        return shipStrength;
    }

    public void recordHitDamage(Player player) {
        this.shipStrength--;
        if (shipStrength == 0){
            shipSunk = true;
            player.newShipSunkOccurrence = true;
        }

    }

    public void setPosCoord(int[] intCoord){
        //record the coordinates of the ship from two input points supplied
        //in an array (x1, y1, x2, y2)
        posCoord =
            CoordinateTool.fillCoordinate(intCoord, posCoord);
    }


}
