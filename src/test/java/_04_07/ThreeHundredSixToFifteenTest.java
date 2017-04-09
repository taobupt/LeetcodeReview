package _04_07;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 4/8/17.
 */
public class ThreeHundredSixToFifteenTest {


    ThreeHundredSixToFifteen THSTF=null;
    @Before
    public void setup(){
        THSTF=new ThreeHundredSixToFifteen();
    }

    @Test
    public void testCountSmaller(){
        int[]nums={5,2,6,1};
        THSTF.countSmaller(nums);
    }

}