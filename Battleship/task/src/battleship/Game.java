package battleship;

import java.awt.geom.Point2D;
import java.util.Scanner;

public class Game {

    boolean gameOver = false;
    GameState gameState = GameState.PLACING_SHIPS;
    GameBoard hiddenBoard = new GameBoard(10, 10);
    GameBoard viewableBoard = new GameBoard(10, 10);
    Scanner scanner = new Scanner(System.in);
    char lastHitOrMiss = BoardSymbol.FOG_OF_WAR.getSymbol();;

    Ship aircraftCarrier = new Ship("Aircraft Carrier", 5);
    Ship battleship = new Ship("Battleship", 4);
    Ship submarine = new Ship("Submarine", 3);
    Ship cruiser = new Ship("Cruiser", 3);
    Ship destroyer = new Ship("Destroyer", 2);
    Ship[] gameShips = {aircraftCarrier, battleship, submarine, cruiser,
        destroyer};

    int totalHitCells;
    int[] hitCoord;

    public Game() {
        for (Ship ship: gameShips) {
            totalHitCells += ship.getShipLength();

        }
    }

    public void playGame(){

        hiddenBoard.printBoard();

        while (!gameOver) {

            switch (gameState){
                case PLACING_SHIPS:
                    placeShips(gameShips);
                    System.out.printf("The game starts!%n%n");
                    viewableBoard.printBoard();
                    gameState = GameState.TAKING_SHOT;
                    break;
                case TAKING_SHOT:
                    hitCoord = takeShot();
                    recordHitsAndMisses(hitCoord);
                    viewableBoard.printBoard();
                    gameState = GameState.CHECKING_GAMEOVER;
                    break;
                case CHECKING_GAMEOVER:
                    if (checkGameOver()){
                        gameState = GameState.EXITING;
                    } else {
                        displayHitOrMiss();
                        gameState = GameState.TAKING_SHOT;
                    }
                    break;
                case EXITING:
                    System.out.printf("You sank the last ship. You won. " +
                            "Congratulations!");
                    gameOver = true;
                    break;
            }
        }
    }

    private void displayHitOrMiss() {
        if (lastHitOrMiss == BoardSymbol.HIT_CELL.getSymbol()){
            System.out.printf("You hit a ship! Try again:%n%n");
        }else if (lastHitOrMiss == BoardSymbol.MISS_CELL.getSymbol()){
            System.out.printf("You missed! Try again:%n%n");
        }
    }

    private void recordHitsAndMisses(int[] hitCoord) {

        if (hiddenBoard.getGameBoard()[hitCoord[0]][hitCoord[1]] ==
                BoardSymbol.SHIP_CELL.getSymbol()){
            hiddenBoard.setGameBoard(hitCoord[0], hitCoord[1],
                    BoardSymbol.HIT_CELL.getSymbol());
            viewableBoard.setGameBoard(hitCoord[0], hitCoord[1],
                    BoardSymbol.HIT_CELL.getSymbol());
            lastHitOrMiss = BoardSymbol.HIT_CELL.getSymbol();
            totalHitCells--;

        } else if (hiddenBoard.getGameBoard()[hitCoord[0]][hitCoord[1]] ==
                BoardSymbol.FOG_OF_WAR.getSymbol()){
            hiddenBoard.setGameBoard(hitCoord[0], hitCoord[1],
                    BoardSymbol.MISS_CELL.getSymbol());
            viewableBoard.setGameBoard(hitCoord[0], hitCoord[1],
                    BoardSymbol.MISS_CELL.getSymbol());
            lastHitOrMiss = BoardSymbol.MISS_CELL.getSymbol();
        }
    }

    private boolean checkGameOver() {
        if (totalHitCells == 0) {
            return true;
        }
        return false;
    }

    private void placeShips(Ship[] gameShips) {

        for (Ship ship: gameShips) {

            int[] shipCoord;
            System.out.printf("Enter the coordinates of the %s (%d cells)%n%n",
                    ship.getType(), ship.getShipLength());

            do {
                shipCoord = inputToIntArrayTwoPoints();
            }while (!checkValidShipCoord(shipCoord, ship));

            ship.setPosCoord(shipCoord);
            hiddenBoard.addShipToBoard(ship);
            hiddenBoard.printBoard();
        }
    }

    private int[] takeShot(){

        int[] hitCoord;

        while (true){
            System.out.printf("Take a shot!%n%n");
            hitCoord = inputToIntArrayOnePoint();
            System.out.println();
            if (hitCoord[0] > hiddenBoard.getGameBoard().length - 1 || hitCoord[1] >
            hiddenBoard.getGameBoard()[0].length){
                System.out.printf("Error! You entered the wrong coordinates! " +
                        "Try again:%n%n");
            }else {
                break;
            }
        }
        return hitCoord;
    }

    private boolean checkValidShipCoord(int[] coordinates, Ship ship){

        int x2 = coordinates[2];
        int y2 = coordinates[3];
        int x1 = coordinates[0];
        int y1 = coordinates[1];

        //input for A1 to A5 would be 0 0 0 4 for horizontal example
        // or A1 to E1, 0 0 4 0 vertical

        //check if valid distance
        if (ship.getShipLength() != twoPointDistance(coordinates)){
            System.out.printf("Error! Wrong length of the %s! Try again:%n%n",
                    ship.getType());
            return false;
        //check if diagonal input
        } else if (x1 != x2 && y2 != y1) {
            System.out.printf("Error! Wrong ship location! Try again:%n%n");
            return false;
        //make sure not placed near another ship
        } else if (checkNearShip(coordinates, ship)){
            System.out.println("Error! You placed it too close to another" +
                    " one. Try again:");
            return false;
        }

        return true;
    }

    int[] inputToIntArrayTwoPoints(){

        //receive input per grid letter and number format and returns it in
        //an array format
        final int aValue = 65;

        String[] stringInput = scanner.nextLine().split(" ");

        int[] intInput = new int[4];

        if (stringInput[0].length() > 2 && stringInput[1].length() == 2){
            intInput[0] = (int) (stringInput[0].charAt(0)) - aValue;
            intInput[1] = hiddenBoard.getGameBoard()[0].length - 1;
            intInput[2] = (int) (stringInput[1].charAt(0)) - aValue;
            intInput[3] = Character.getNumericValue(stringInput[1]
                    .charAt(1)) - 1;
            return intInput;
        } else if (stringInput[1].length() > 2 && stringInput[0].length() == 2){
            intInput[0] = (int) (stringInput[0].charAt(0)) - aValue;
            intInput[1] = Character.getNumericValue(stringInput[0]
                    .charAt(1)) - 1;
            intInput[2] = (int) (stringInput[1].charAt(0)) - aValue;
            intInput[3] = hiddenBoard.getGameBoard()[0].length - 1;
            return intInput;
        } else if (stringInput[0].length() > 2 && stringInput[1].length() > 2){
            intInput[0] = (int) (stringInput[0].charAt(0)) - aValue;
            intInput[1] = hiddenBoard.getGameBoard()[0].length - 1;
            intInput[2] = (int) (stringInput[1].charAt(0)) - aValue;
            intInput[3] = hiddenBoard.getGameBoard()[0].length - 1;
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

    int[] inputToIntArrayOnePoint(){

        //receive input per grid letter and number format and returns it in
        //an array format
        final int aValue = 65;

        String stringInput = scanner.nextLine();

        int[] intInput = new int[2];

        if (stringInput.length() > 2){
            intInput[0] = (int) (stringInput.charAt(0)) - aValue;
            intInput[1] = Integer.parseInt(stringInput.substring(1)) - 1;
            return intInput;
        } else {
            intInput[0] = (int) (stringInput.charAt(0)) - aValue;
            intInput[1] = Character.getNumericValue(stringInput
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
            , hiddenBoard.getGameBoard())){
                return true;
            }
        }
        return false;
    }
}
