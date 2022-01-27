package battleship;

public class Game {

    boolean gameOver = false;
    GameState gameState = GameState.PRINTING_BOARD;
    GameBoard board = new GameBoard(10, 10);

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
        System.out.println("Placing ships!");
    }
}
