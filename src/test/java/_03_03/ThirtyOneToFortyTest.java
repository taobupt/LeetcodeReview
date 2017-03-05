package _03_03;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by taobupt on 3/3/2017.
 */
public class ThirtyOneToFortyTest {

    public ThirtyOneToForty tf=null;

    @Before
    public void setup(){
        tf=new ThirtyOneToForty();
    }

    @Test
    public void upperBound() throws Exception {
        int []nums={1,2,3,4,5};
        assertEquals(tf.searchInsert(nums,6),tf.serachIntRecursive(nums,6));
        System.out.println(tf.upperBound(nums,0));
    }
    @Test
    public void testCountAndSay()throws Exception{
        System.out.println(tf.countAndSay(5));
    }
    @Test
    public void testSuduko()throws Exception{
        //["53..7....","6..195...",".98....6.","8...6...3","4..8.3..1","7...2...6",".6....28.","...419..5","....8..79"]
        char [][]board={{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        System.out.println(tf.isValidSudoku(board));
    }

    @Test
    public void testLongest()throws  Exception{
        System.out.println(tf.longestValidParentheses(")()())"));
    }

    @Test
    public void testSudu()throws Exception{

        char [][]board={{'.','.','9','7','4','8','.','.','.'},{'7','.','.','.','.','.','.','.','.'},{'.','2','.','1','.','9','.','.','.'},{'.','.','7','.','.','.','2','4','.'},{'.','6','4','.','1','.','5','9','.'},{'.','9','8','.','.','.','3','.','.'},{'.','.','.','8','.','3','.','2','.'},{'.','.','.','.','.','.','.','.','6'},{'.','.','.','2','7','5','9','.','.'}};
        tf.solveSudoku(board);
    }

}