package _04_22;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 5/25/17.
 */
public class FourHundredFiftySixToFourHundredSixtyFiveTest {


    FourHundredFiftySixToFourHundredSixtyFive FHFSTFHSF=null;

    @Before
    public void setup(){
        FHFSTFHSF = new FourHundredFiftySixToFourHundredSixtyFive();
    }

    @Test
    public void repeatedSubstringPattern() throws Exception {
        FHFSTFHSF.repeatedSubstringPattern("ababa");
    }

    @Test
    public void testHamming(){
        System.out.println(FHFSTFHSF.hammingDistance(1,4));
    }

}