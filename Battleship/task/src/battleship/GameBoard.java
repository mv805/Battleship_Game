package battleship;

import java.util.Arrays;

public class GameBoard {

    char[][] gameBoard;
    Ship[] shipsOnBoard = new Ship[5];

    public GameBoard(int width, int height) {

        this.gameBoard = new char[width][height];
        fillBoardDefault('~');
    }

    void fillBoardDefault(char fillChar){
        for (int i = 0; i < gameBoard.length; i++){
            for (int j = 0; j < gameBoard[i].length; j++){
                gameBoard[i][j] = '~';
            }
        }
    }

    void printBoard(){
        char indexChar = 'A';
        //top line index
        System.out.print("  ");
        for (int i = 0; i < gameBoard.length; i++){
            System.out.printf("%d ", i + 1);
        }
        System.out.println();

        //array with vertical index
        for (int i = 0; i < gameBoard.length; i++){
            System.out.print(indexChar + " ");
            for (int j = 0; j < gameBoard[i].length; j++){
                System.out.printf("%c ", gameBoard[i][j]);
            }
            System.out.println();
            indexChar++;
        }
        System.out.println();
    }

    void addShipToBoard(Ship ship){
        /*
            array

             i |
               V                 x y coordinates
            [0, 0] [0, 1] <-j    (0) (0) x1, y1
            [1, 0] [1, 1]        (0) (1)
            [2, 0] [2, 1]        (0) (2)
            [3, 0] [3, 1]        (0) (3)
            [4, 0] [4, 1]        (0) (4) x2, y2
        */
        /* SET FOR REMOVAL
        for (int i = 0; i < ship.posCoord.length; i++){

            int posX1 = ship.posCoord[i][0];
            int posY1 = ship.posCoord[i][1];

            gameBoard[posX1][posY1] =
                    BoardSymbol.SHIP_CELL.getSymbol();

        }*/

    }




}
