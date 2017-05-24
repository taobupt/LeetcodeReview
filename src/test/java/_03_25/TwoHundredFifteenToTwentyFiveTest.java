package _03_25;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/25/17.
 */
public class TwoHundredFifteenToTwentyFiveTest {

    TwoHundredFifteenToTwentyFive TFTF=null;
    @Before
    public void setup(){
            TFTF=new TwoHundredFifteenToTwentyFive();
    }

    @Test
    public void containsDuplicate() throws Exception {
        TFTF.combinationSum3(3,9);
        int []nums={1,2,3};
        TFTF.rob(nums);
    }

    @Test
    public void testBuildings()throws Exception{
        int [][]buildings={{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        TFTF.getSkyline(buildings);
    }

}