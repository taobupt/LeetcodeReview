package design;

/**
 * Created by tao on 4/18/17.
 */


public class TicTacToe {


    //有n行，n列，2条对角线
    //其实diagonal 应该也是个一维数组的。
    //其实antidiagonal 应该也是个一维数组的。
    private int[][]rows=null;
    private int[][]cols=null;
    private int diagonal;
    private int antidiagonal;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rows=new int[n][2];
        cols=new int[n][2];
        diagonal=0;
        antidiagonal=0;
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        rows[row][player-1]++;
        cols[col][player-1]++;
        if(rows[row][player-1]==rows.length)
            return player;
        if(cols[col][player-1]==cols.length)
            return player;
        if(row==col){
            if(player==1)
                diagonal++;
            else
                diagonal--;
            if(diagonal==rows.length)
                return 1;
            else if(diagonal==-rows.length)
                return 2;
        }
        if(row+col+1==rows.length){
            if(player==1)
                antidiagonal++;
            else
                antidiagonal--;
            if(antidiagonal==rows.length)
                return 1;
            else if(antidiagonal==-rows.length)
                return 2;

        }
        return 0;

    }
}
