package _04_24;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 5/26/17.
 */
public class FourHundredSeventySixToFourHundredEightyFiveTest {


    FourHundredSeventySixToFourHundredEightyFive fhsstfhef=null;

    @Before
    public void setup(){
        fhsstfhef = new FourHundredSeventySixToFourHundredEightyFive();
    }

    @Test
    public void findPermutation() throws Exception {
        fhsstfhef.findPermutation("DDIIDI");
    }

    @Test
    public void testMagic(){
        fhsstfhef.magicalString(10);
    }

    @Test
    public void testMedian(){
        int[]nums={1,2147483647,2147483647,2};
        fhsstfhef.medianSlidingWindow(nums,2);
    }

    @Test
    public void testGoodBase(){
        System.out.println(fhsstfhef.smallestGoodBase("15"));
    }
}