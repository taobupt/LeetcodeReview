package _04_10;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 4/17/17.
 */
public class ThreeHundredThirtysixToFourtyFiveTest {

    ThreeHundredThirtysixToFourtyFive TTSTFF=null;

    @Before
    public void setup(){
        TTSTFF=new ThreeHundredThirtysixToFourtyFive();
    }
    @Test
    public void lengthOfLongestSubstringKDistinct() throws Exception {
        TTSTFF.lengthOfLongestSubstringKDistinct("eceba",2);
    }

}