package battleship;

import java.util.Arrays;

public class GameBoard {

    char[][] gameBoard;

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


    }




}
