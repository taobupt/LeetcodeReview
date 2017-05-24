package _04_19;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 5/23/17.
 */
public class FourHundredTwentySixToFourHundredThirtyFiveTest {

    FourHundredTwentySixToFourHundredThirtyFive fhtstfhtf=null;

    @Before
    public void setup(){
        fhtstfhtf=new FourHundredTwentySixToFourHundredThirtyFive();
    }
    @Test
    public void countSegments() throws Exception {
        String s=" ";
        System.out.println(fhtstfhtf.countSegmentsWithSpace(s));
    }

}