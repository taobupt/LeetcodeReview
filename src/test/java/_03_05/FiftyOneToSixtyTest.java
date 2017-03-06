package _03_05;

import common.Interval;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/5/17.
 */
public class FiftyOneToSixtyTest {
    FiftyOneToSixty ft=null;

    @Before
    public void setup(){
        ft=new FiftyOneToSixty();
    }

    @Test
    public void solveNQueens() throws Exception {
        ft.solveNQueens(4);
    }

    @Test
    public void testSubarrysSum()throws Exception{
        int []nums={-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(ft.maxSubarrayConquer2(nums));
    }

    @Test
    public void testMerge()throws Exception{
        int [][]maxtrix={{1,2,3},{4,5,6},{7,8,9}};
        ft.spiralOrder(maxtrix);
    }

    @Test
    public void testSpiral()throws Exception{
        ft.generateMatrix(3);
        System.out.println(ft.getPermutation(3,6));
    }

}