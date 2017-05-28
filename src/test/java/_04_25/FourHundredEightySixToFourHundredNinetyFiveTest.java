package _04_25;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 5/27/17.
 */
public class FourHundredEightySixToFourHundredNinetyFiveTest {

    FourHundredEightySixToFourHundredNinetyFive fhestfhnf=null;

    @Before
    public void setup(){
        fhestfhnf =  new FourHundredEightySixToFourHundredNinetyFive();
    }

    @Test
    public void findMaxConsecutiveOnes() throws Exception {
        int []nums= {1,0,1,1,0,1};
        System.out.println(fhestfhnf.findMaxConsecutiveOnes(nums));
    }

}