package battleship;

public class CoordinateTool {

    public static int[][] returnCoordinate(int[] intCoord, int[][] posCoordInput){
        //with two points supplied in a 4 digit array (intCoord), return a multi
        //dim array of coordinate points (posCoord) of all points between the
        // two original points.

        /*   EXAMPLES OF MULTI DIM ARRAY COORDINATES VS INPUT COORDINATES VISUAL

            array
             i |                 left to right example  top to bottom example
               V                 x y coordinates        x y coordinates
            [0, 0] [0, 1] <-j    (5) (3) x1, y1         (0) (0) x1, y1
            [1, 0] [1, 1]        (5) (4)                (1) (0)
            [2, 0] [2, 1]        (5) (5)                (2) (0)
            [3, 0] [3, 1]        (5) (6)                (3) (0)
            [4, 0] [4, 1]        (5) (7) x2, y2         (4) (0) x2, y2

        */

        int x2 = 2;
        int y1 = 1;
        int y2 = 3;
        int x1 = 0;

        //if horizontal left to right
        if (intCoord[x1] == intCoord[x2] && intCoord[y1] < intCoord[y2]){
            fillLeftToRight(intCoord[x1],intCoord[y1], posCoordInput);
            //if horizontal right to left
        } else if (intCoord[x1] == intCoord[x2] && intCoord[y1] > intCoord[y2]){
            fillLeftToRight(intCoord[x2],intCoord[y2], posCoordInput);
            //if vertical top to bottom
        } else if (intCoord[y1] == intCoord[y2] && intCoord[x1] < intCoord[x2]){
            fillTopToBottom(intCoord[x1],intCoord[y1], posCoordInput);
            //if vertical bottom to top
        } else if (intCoord[y1] == intCoord[y2] && intCoord[x1] > intCoord[x2]){
            fillTopToBottom(intCoord[x2],intCoord[y2], posCoordInput);
        }

        return posCoordInput;
    }
    private static void fillLeftToRight(int x, int y, int[][] posCoord){
        //for filling the posCoord array with location info from left to right
        //relative to the board layout
        for (int i = 0; i < posCoord.length; i++){
            posCoord[i][0] = x;
            posCoord[i][1] = y + i;
        }
    }
    private static void fillTopToBottom(int x, int y, int[][] posCoord){
        //for filling the posCoord array with location info from top to bottom
        //relative to the board layout
        for (int i = 0; i < posCoord.length; i++){
            posCoord[i][0] = x + i;
            posCoord[i][1] = y;
        }
    }

    public static boolean fivePointCheck(int row, int col, char[][] board) {

        if (board[row][col] == BoardSymbol.SHIP_CELL.getSymbol()){
            return true;
        }else if (row != 0 && board[row - 1][col] ==
                BoardSymbol.SHIP_CELL.getSymbol()){
            return true;
        } else if (col != 9 && board[row][col + 1] ==
                BoardSymbol.SHIP_CELL.getSymbol()){
            return true;
        }else if (row != 9 && board[row + 1][col] ==
                BoardSymbol.SHIP_CELL.getSymbol()){
            return true;
        }else if (col != 0 && board[row][col - 1] ==
                BoardSymbol.SHIP_CELL.getSymbol()){
            return true;
        } else {
            return false;
        }
    }
}
