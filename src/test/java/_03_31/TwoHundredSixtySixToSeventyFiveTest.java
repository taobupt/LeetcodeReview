package _03_31;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Tao on 3/31/2017.
 */
public class TwoHundredSixtySixToSeventyFiveTest {


    TwoHundredSixtySixToSeventyFive THSSTS=null;

    @Before
    public void setup(){
        THSSTS=new TwoHundredSixtySixToSeventyFive();
    }

    @Test
    public void testPalindromePermutation(){
        //THSSTS.canPermutateByBitSet("abc");
        THSSTS.generatePalindromes("aaa");
    }

    @Test
    public void testhINDEX(){
        int[]nums={0,0,0,0,0,0,1};
        System.out.println(THSSTS.hIndexII(nums));
    }

}
