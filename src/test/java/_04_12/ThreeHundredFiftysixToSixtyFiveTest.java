package _04_12;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 4/19/17.
 */
public class ThreeHundredFiftysixToSixtyFiveTest {

    ThreeHundredFiftysixToSixtyFive THFSTSF=null;

    @Before
    public void setup(){
        THFSTSF=new ThreeHundredFiftysixToSixtyFive();
    }
    @Test
    public void testTransformed()throws Exception{
        int[]nums={-4,-2,2,4};
        THFSTSF.sortTransformedArray(nums,0,0,1);
    }

    @Test
    public void testWaterANDJugs()throws Exception{
        System.out.println(THFSTSF.canMeasureWater(0,0,1));
    }

    @Test
    public void testFindMatrix()throws Exception{
        int[][]matrix={{1,2,-1,-4,-20},{-8,-3,4,2,1},{3,8,10,1,3},{-4,-1,1,7,-6}};
        System.out.println(THFSTSF.findMaxSubMatrix(matrix));
    }

    @Test
    public void testRearrangeString()throws Exception{
        System.out.println(THFSTSF.rearrangeString("aaadbbcc",2));
    }


}