package battleship;

import java.awt.geom.Point2D;
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
            System.out.printf("Enter the coordinates of the %s (%d cells)%n%n",
                    ship.getType(), ship.getShipLength());

            do {
                shipCoord = inputToIntArray();
            }while (!checkValidShipCoord(shipCoord, ship));
            
            //hand coordinates to board to place
            ship.setPosCoord(shipCoord);

            //hand coordinates to board to place
            board.addShipToBoard(ship);
            board.printBoard();
        }
    }

    private boolean checkValidShipCoord(int[] coordinates, Ship ship){

        int x2 = coordinates[2];
        int y2 = coordinates[3];
        int x1 = coordinates[0];
        int y1 = coordinates[1];

        //input for A1 to A5 would be 0 0 0 4 for horizontal example
        // or A1 to E1, 0 0 4 0 vertical

        //logic to check for:
        // diagonal,[ok]
        // too close,
        // right distance [OK]
        // not crossing another ship

        //check if valid distance
        if (ship.getShipLength() != twoPointDistance(coordinates)){
            System.out.printf("Error! Wrong length of the %s! Try again:%n%n",
                    ship.getType());
            return false;
        //check if diagonal input
        } else if (x1 != x2 && y2 != y1) {
            System.out.printf("Error! Wrong ship location! Try again:%n%n");
            return false;
        } else if (checkNearShip(coordinates, ship)){
            System.out.println("Error! You placed it too close to another" +
                    " one. Try again:");
            return false;
        }

        return true;
    }

    int[] inputToIntArray(){

        //receive input per grid letter and number format and returns it in
        //an array format
        final int aValue = 65;
        int[] intInput = new int[4];

        String[] stringInput = scanner.nextLine().split(" ");

        if (stringInput[0].length() > 2 && stringInput[1].length() == 2){
            intInput[0] = (int) (stringInput[0].charAt(0)) - aValue;
            intInput[1] = board.gameBoard[0].length - 1;
            intInput[2] = (int) (stringInput[1].charAt(0)) - aValue;
            intInput[3] = Character.getNumericValue(stringInput[1]
                    .charAt(1)) - 1;
            return intInput;
        } else if (stringInput[1].length() > 2 && stringInput[0].length() == 2){
            intInput[0] = (int) (stringInput[0].charAt(0)) - aValue;
            intInput[1] = Character.getNumericValue(stringInput[0]
                    .charAt(1)) - 1;
            intInput[2] = (int) (stringInput[1].charAt(0)) - aValue;
            intInput[3] = board.gameBoard[0].length - 1;
            return intInput;
        } else if (stringInput[0].length() > 2 && stringInput[1].length() > 2){
            intInput[0] = (int) (stringInput[0].charAt(0)) - aValue;
            intInput[1] = board.gameBoard[0].length - 1;
            intInput[2] = (int) (stringInput[1].charAt(0)) - aValue;
            intInput[3] = board.gameBoard[0].length - 1;
            return intInput;
        } else {
            intInput[0] = (int) (stringInput[0].charAt(0)) - aValue;
            intInput[1] = Character.getNumericValue(stringInput[0]
                    .charAt(1)) - 1;
            intInput[2] = (int) (stringInput[1].charAt(0)) - aValue;
            intInput[3] = Character.getNumericValue(stringInput[1]
                    .charAt(1)) - 1;
            return intInput;
        }
    }

    int twoPointDistance(int[] array){

        // distance between two points stored in 4 digit array
        // with format X1, Y1, X2, Y2

        int x2 = array[2];
        int y2 = array[3];
        int x1 = array[0];
        int y1 = array[1];

        return (int) Point2D.distance(x1, y1, x2, y2) + 1;
    }

    boolean checkNearShip(int[] coordinates, Ship ship){

        int[][] posCoord = new int[ship.getShipLength()][2];
        posCoord = CoordinateTool.returnCoordinate(coordinates,posCoord);

        for (int i = 0; i < posCoord.length; i++){
            if (CoordinateTool.fivePointCheck(posCoord[i][0], posCoord[i][1]
            , board.gameBoard)){
                return true;
            }
        }
        return false;
    }
}
