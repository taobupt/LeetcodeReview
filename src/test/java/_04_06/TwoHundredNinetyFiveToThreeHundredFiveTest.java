package _04_06;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 4/6/17.
 */
public class TwoHundredNinetyFiveToThreeHundredFiveTest {
    TwoHundredNinetyFiveToThreeHundredFive THN=null;

    @Before
    public void setup(){
        THN=new TwoHundredNinetyFiveToThreeHundredFive();
    }

    @Test
    public void lengthOfLIS() throws Exception {
        int[]nums={1,3,6,7,9,4,10,5,6};
        System.out.println(THN.lengthOfLIS(nums));
    }

    @Test
    public void testPixel(){
        char[][]image={{'0','0','1','0'},{'0','1','1','0'},{'0','1','0','0'}};
        System.out.println(THN.minArea(image,0,2));
    }

}