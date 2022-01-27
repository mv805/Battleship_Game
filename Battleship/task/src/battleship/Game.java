package battleship;

import java.util.Scanner;

public class Game {

    boolean gameOver = false;
    GameState gameState = GameState.PRINTING_BOARD;
    GameBoard board = new GameBoard(10, 10);
    Scanner scanner = new Scanner(System.in);

    Ship aircraftCarrier = new Ship("Aircraft Carrier", 5);
    Ship battleship = new Ship("Battleship", 4);
    Ship submarine = new Ship("Submarine", 3);
    Ship cruiser = new Ship("Cruiser", 3);
    Ship destroyer = new Ship("Destroyer", 2);
    Ship[] gameShips = {aircraftCarrier, battleship, submarine, cruiser,
        destroyer};


    public void playGame(){

        while (!gameOver) {

            switch (gameState){
                case PRINTING_BOARD:
                    board.printBoard();
                    gameState = GameState.PLACING_SHIPS;
                    break;
                case PLACING_SHIPS:
                    placeShips(gameShips);
                    gameState = GameState.EXITING;
                    break;
                case EXITING:
                    gameOver = true;
                    break;
            }
        }
    }

    private void placeShips(Ship[] gameShips) {

        for (Ship ship: gameShips) {
            int[] shipCoord;
            do {

                //prompt for input
                promptShipPlacement(ship);

                //define the ship coordinates
                shipCoord = convertToArrayIndex(scanner.nextLine().replaceAll("\\s", "")
                        .toCharArray());

            }while (!checkValidShipCoord(shipCoord));

            //hand coordinates to board to place
            ship.defineEndpoints(shipCoord);
            board.addShipToBoard(ship);
        }
    }

    private void promptShipPlacement(Ship ship){
        System.out.printf("Enter the coordinates of the %s (%d cells)%n",
                ship.getType(), ship.getShipStatus().length);
    }

    private boolean checkValidShipCoord(int[] coordinates){

        //check if valid
        //logic to : check for diagonal, check for too close, check for long
        //enough, check for not crossing
        //print appropriate error if false
        return true;
    }

    int[] convertToArrayIndex(char[] shipCoord) {
        int aIndex = 65;
        return new int[] { (int) shipCoord[0] - aIndex,
                Character.getNumericValue(shipCoord[1]),
                (int) shipCoord[2] - aIndex,
                Character.getNumericValue(shipCoord[3])};
    }
}
