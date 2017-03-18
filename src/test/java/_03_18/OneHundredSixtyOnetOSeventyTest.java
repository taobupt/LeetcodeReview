package _03_18;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/17/17.
 */
public class OneHundredSixtyOnetOSeventyTest {

    OneHundredSixtyOnetOSeventy osos=null;
    @Before
    public void setup(){
        osos=new OneHundredSixtyOnetOSeventy();
    }
    @Test
    public void majorityElementII() throws Exception {
        int []nums={8,8,7,7,7};
        osos.majorityElementII(nums);
    }

    @Test
    public void testMissingRanges()throws Exception{
        int []nums={0,1,3,50,75};
        osos.findMissingRanges(nums,0,99);
    }

}