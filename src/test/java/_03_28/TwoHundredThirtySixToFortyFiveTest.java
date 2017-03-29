package _03_28;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/29/17.
 */
public class TwoHundredThirtySixToFortyFiveTest {

    TwoHundredThirtySixToFortyFive TTSTF=null;
    @Test
    public void diffWaysToCompute() throws Exception {
        TTSTF =new TwoHundredThirtySixToFortyFive();
        TTSTF.diffWaysToCompute("-2");
    }

}