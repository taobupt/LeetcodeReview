package _04_03;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 4/3/17.
 */
public class TwoHundredEightySixToNinetyFiveTest {

    TwoHundredEightySixToNinetyFive TESTNF=null;

    @Before
    public void setup(){
        TESTNF=new TwoHundredEightySixToNinetyFive();
    }
    @Test
    public void findDuplicate() throws Exception {
        int[]nums={2,1,1};
        System.out.println(TESTNF.findDuplicate(nums));
    }

    @Test
    public void wallsAndGates()throws Exception{
        int[][]rooms={{2147483647,-1,0,2147483647},{2147483647,2147483647,2147483647,-1},{2147483647,-1,2147483647,-1},{0,-1,2147483647,2147483647}};
        TESTNF.wallsAndGates(rooms);
    }

    @Test
    public void testWordMatch()throws Exception{
        //"itwasthebestoftimes"
        //"ittwaastthhebesttoofttimesss"
        System.out.println(TESTNF.wordPatternMatch("itwasthebestoftimes","ittwaastthhebesttoofttimesss"));
    }

}