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

}