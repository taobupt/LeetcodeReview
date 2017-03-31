package _03_30;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Tao on 3/30/2017.
 */
public class TwoHundredFiftySixToSixtyFiveTest {

    TwoHundredFiftySixToSixtyFive TFSTF=null;
    @Before
    public void setup(){
        TFSTF=new TwoHundredFiftySixToSixtyFive();
    }

    @Test
    public void testAddDigits(){
        TFSTF.addDigits(2);
    }

    @Test
    public void testThreeSumSmaller()throws Exception{
        int []nums={3,-2,-2};

        System.out.println(TFSTF.threeSumSmaller(nums,-1));
    }

    @Test
    public void testNthUgly()throws Exception{
        for(int i=1;i<=10;++i)
            System.out.println(TFSTF.nthUglyNumberConcise(i));
    }
}
