package battleship;

import java.awt.geom.Point2D;
import java.util.Scanner;

public class Game {

    boolean gameOver = false;
    private GameState gameState = GameState.PLACING_SHIPS;
    private CurrentPlayer currentPlayer;
    final public static Scanner scanner = new Scanner(System.in);
    private Player player1 = new Player("Player 1");
    private Player player2 = new Player("Player 2");

    public void playGame(){

        while (!gameOver) {

            switch (gameState){
                case PLACING_SHIPS:
                    placeShips(player1);
                    placeShips(player2);
                    currentPlayer = CurrentPlayer.PLAYER_1;
                    gameState = GameState.TAKING_SHOT;
                    break;
                case TAKING_SHOT:
                    if (currentPlayer == CurrentPlayer.PLAYER_1){
                        firingSequence(player1, player2);
                    } else if (currentPlayer == CurrentPlayer.PLAYER_2){
                        firingSequence(player2, player1);
                    }
                    gameState = GameState.CHECKING_GAMEOVER;
                    break;
                case CHECKING_GAMEOVER:
                    if (currentPlayer == CurrentPlayer.PLAYER_1){
                        gameOverCheckSequence(player1);
                        currentPlayer = CurrentPlayer.PLAYER_2;
                        gameState = GameState.TAKING_SHOT;
                    } else if (currentPlayer == CurrentPlayer.PLAYER_2){
                        gameOverCheckSequence(player2);
                        currentPlayer = CurrentPlayer.PLAYER_1;
                        gameState = GameState.TAKING_SHOT;
                    }
                    break;
                case EXITING:
                    gameOver = true;
                    break;
            }
        }
    }

    private void gameOverCheckSequence(Player player){

        if (player.checkIfShipSunk() && player.totalHitCells == 0){
            System.out.print("You sank the last ship. You won. " +
                    "Congratulations!");
            gameState = GameState.EXITING;
        }else if (player.checkIfShipSunk()){
            System.out.println("You sank a ship!");
        }else if (player.lastHitOrMiss == BoardSymbol.MISS_CELL.getSymbol()){
            System.out.printf("You missed!%n%n");
        }else if (player.lastHitOrMiss == BoardSymbol.HIT_CELL.getSymbol()){
            System.out.printf("You hit a ship!%n%n");
            System.out.println(player.checkIfShipSunk());
        }
        pauseForEnter();
    }

    private void pauseForEnter(){
        System.out.printf("Press Enter and pass the move to another " +
                "player%n%n");
        scanner.nextLine();
    }

    private void firingSequence(Player playerFiring, Player playerReceiving){

        playerReceiving.publicBoard.printBoard();
        System.out.printf("---------------------%n");
        playerFiring.privateBoard.printBoard();
        playerFiring.hitCoord = takeShot(playerFiring);
        recordHitsAndMisses(playerFiring.hitCoord, playerFiring,
                playerReceiving);

    }

    private void recordHitsAndMisses(int[] hitCoord, Player playerFiring,
                                     Player playerReceiving) {

        if (playerReceiving.privateBoard.getGameBoard()[hitCoord[0]][hitCoord[1]] ==
                BoardSymbol.SHIP_CELL.getSymbol()){
            playerReceiving.publicBoard.setGameBoard(hitCoord[0], hitCoord[1],
                    BoardSymbol.HIT_CELL.getSymbol());
            playerReceiving.privateBoard.setGameBoard(hitCoord[0], hitCoord[1],
                    BoardSymbol.HIT_CELL.getSymbol());
            playerFiring.lastHitOrMiss = BoardSymbol.HIT_CELL.getSymbol();
            playerReceiving.recordShipHit(hitCoord);
            playerReceiving.totalHitCells--;

        } else if (playerReceiving.privateBoard.getGameBoard()[hitCoord[0]][hitCoord[1]] ==
                BoardSymbol.FOG_OF_WAR.getSymbol()){
            playerReceiving.publicBoard.setGameBoard(hitCoord[0], hitCoord[1],
                    BoardSymbol.MISS_CELL.getSymbol());
            playerReceiving.privateBoard.setGameBoard(hitCoord[0], hitCoord[1],
                    BoardSymbol.MISS_CELL.getSymbol());
            playerFiring.lastHitOrMiss = BoardSymbol.MISS_CELL.getSymbol();
        }
    }

    private void placeShips(Player player) {

        System.out.printf("%s, place your ships on the game field%n%n",
                player.getPlayerName());
        player.publicBoard.printBoard();
        System.out.println();

        for (Ship ship: player.gameShips) {

            int[] shipCoord;
            System.out.println();
            System.out.printf("Enter the coordinates of the %s (%d cells)%n%n",
                    ship.getType(), ship.getShipLength());
            System.out.println();

            do {
                shipCoord = inputToIntArrayTwoPoints(player);
            }while (!checkValidShipCoord(shipCoord, ship, player));

            ship.setPosCoord(shipCoord);
            player.privateBoard.addShipToBoard(ship);
            player.privateBoard.printBoard();
        }

        pauseForEnter();
    }

    private int[] takeShot(Player player){

        //returns a single valid attack coordinate i.e. D3(4,2), A6(0,5),  etc.
        final int aValue = 65;
        int[] hitCoord = new int[2];
        System.out.printf("%s, its your turn:%n%n", player.getPlayerName());

        while (true) {

            String stringInput = scanner.nextLine();
            System.out.println();

            if (stringInput.length() == 2) {
                hitCoord[0] = (int) (stringInput.charAt(0)) - aValue;
                hitCoord[1] = Character.getNumericValue(stringInput
                        .charAt(1)) - 1;
                break;
            } else if (stringInput.length() > 2) {
                hitCoord[0] = (int) (stringInput.charAt(0)) - aValue;
                hitCoord[1] = Integer.parseInt(stringInput.substring(1)) - 1;

                if (hitCoord[1] != 10){
                    System.out.printf("Error! You entered the wrong coordinates! " +
                            "Try again:%n%n");
                    continue;
                } else {
                    hitCoord[0] = (int) (stringInput.charAt(0)) - aValue;
                    hitCoord[1] = 10;
                    break;
                }
            } else{
                System.out.printf("Error! You entered the wrong coordinates! " +
                        "Try again:%n%n");
            }

        }

        return hitCoord;
    }

    private boolean checkValidShipCoord(int[] coordinates, Ship ship,
                                        Player player){

        int x2 = coordinates[2];
        int y2 = coordinates[3];
        int x1 = coordinates[0];
        int y1 = coordinates[1];

        //input for A1 to A5 would be 0 0 0 4 for horizontal example
        // or A1 to E1, 0 0 4 0 vertical

        //check if valid distance
        if (ship.getShipLength() != twoPointDistance(coordinates)){
            System.out.println();
            System.out.printf("Error! Wrong length of the %s! Try again:%n%n",
                    ship.getType());
            return false;
        //check if diagonal input
        } else if (x1 != x2 && y2 != y1) {
            //System.out.println();
            System.out.printf("Error! Wrong ship location! Try again:%n%n");
            System.out.println();
            return false;
        //make sure not placed near another ship
        } else if (checkNearShip(coordinates, ship, player)){
            System.out.println();
            System.out.println("Error! You placed it too close to another" +
                    " one. Try again:");
            return false;
        }

        return true;
    }

    int[] inputToIntArrayTwoPoints(Player player){

        //receive input per grid letter and number format and returns it in
        //an array format
        final int aValue = 65;

        String[] stringInput = scanner.nextLine().split(" ");

        int[] intInput = new int[4];

        if (stringInput[0].length() > 2 && stringInput[1].length() == 2){
            intInput[0] = (int) (stringInput[0].charAt(0)) - aValue;
            intInput[1] = player.privateBoard.getGameBoard()[0].length - 1;
            intInput[2] = (int) (stringInput[1].charAt(0)) - aValue;
            intInput[3] = Character.getNumericValue(stringInput[1]
                    .charAt(1)) - 1;
            return intInput;
        } else if (stringInput[1].length() > 2 && stringInput[0].length() == 2){
            intInput[0] = (int) (stringInput[0].charAt(0)) - aValue;
            intInput[1] = Character.getNumericValue(stringInput[0]
                    .charAt(1)) - 1;
            intInput[2] = (int) (stringInput[1].charAt(0)) - aValue;
            intInput[3] = player.privateBoard.getGameBoard()[0].length - 1;
            return intInput;
        } else if (stringInput[0].length() > 2 && stringInput[1].length() > 2){
            intInput[0] = (int) (stringInput[0].charAt(0)) - aValue;
            intInput[1] = player.privateBoard.getGameBoard()[0].length - 1;
            intInput[2] = (int) (stringInput[1].charAt(0)) - aValue;
            intInput[3] = player.privateBoard.getGameBoard()[0].length - 1;
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

    boolean checkNearShip(int[] coordinates, Ship ship, Player player){

        int[][] posCoord = new int[ship.getShipLength()][2];
        posCoord = CoordinateTool.fillCoordinate(coordinates,posCoord);

        for (int i = 0; i < posCoord.length; i++){
            if (CoordinateTool.fivePointCheck(posCoord[i][0], posCoord[i][1]
            , player.privateBoard.getGameBoard())){
                return true;
            }
        }
        return false;
    }

    enum CurrentPlayer{
        PLAYER_1,
        PLAYER_2
    }
}
