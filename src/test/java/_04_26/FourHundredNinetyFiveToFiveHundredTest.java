package _04_26;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 5/30/17.
 */
public class FourHundredNinetyFiveToFiveHundredTest {

    FourHundredNinetyFiveToFiveHundred FHNFTFH =null;
    @Before
    public void setup(){
        FHNFTFH = new FourHundredNinetyFiveToFiveHundred();
    }


    @Test
    public void nextGreaterElement() throws Exception {
        int []findNums ={4,1,2};
        int []nums ={1,3,4,2};
        int []res = FHNFTFH.nextGreaterElement(findNums,nums);
        for(int x:res)
            System.out.println(x);
    }

    @Test
    public void testTraversalDiagonal(){
        int [][]matrix ={{1,2,3},{4,5,6},{7,8,9}};
        FHNFTFH.findDiagonalOrder(matrix);
    }

}