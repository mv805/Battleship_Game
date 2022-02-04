package battleship;

public class Player {


    public boolean newShipSunkOccurrence = false;
    String playerName;
    GameBoard privateBoard = new GameBoard(10, 10);
    GameBoard publicBoard = new GameBoard(10, 10);

    Ship aircraftCarrier = new Ship("Aircraft Carrier", 5);
    Ship battleship = new Ship("Battleship", 4);
    Ship submarine = new Ship("Submarine", 3);
    Ship cruiser = new Ship("Cruiser", 3);
    Ship destroyer = new Ship("Destroyer", 2);
    Ship[] gameShips = {aircraftCarrier, battleship, submarine, cruiser,
            destroyer};
    //boolean[] shipStatus = {aircraftCarrier.shipSunk, battleship.shipSunk,
           // submarine.shipSunk, cruiser.shipSunk, destroyer.shipSunk};

    //int totalShipsSunk = shipStatus.length;

    int totalHitCells;
    int[] hitCoord;
    char lastHitOrMiss = BoardSymbol.FOG_OF_WAR.getSymbol();

    public Player(String playerName) {

        this.playerName = playerName;

        for (Ship ship: gameShips) {
            totalHitCells += ship.getShipLength();
        }

    }

    public String getPlayerName() {
        return playerName;
    }

    public void recordShipHit(int[] hitCoord, Player player){
        //hit coord is (3, 5) format
        //record the strike on a ship
        /*for (int num: hitCoord) {
            System.out.println(num);
        }*/
        for (Ship ship: gameShips) {

            //System.out.println(ship.getType());
            //CoordinateTool.printTwoDimArray(ship.getPosCoord());

            for (int i = 0; i < ship.getPosCoord().length; i++){
                if (ship.getPosCoord()[i][0] == hitCoord[0] &&
                        ship.getPosCoord()[i][1] == hitCoord[1] ){
                    ship.recordHitDamage(player);
                    return;
                }
            }
        }
    }

    public boolean checkIfShipSunk() {
        //check if there are any changes to sunk ships or not
        for (Ship ship: gameShips) {
            if (ship.shipSunk){
                ship.shipSunk = true;
                return true;
            }
        }
        return false;
    }
}
