package _03_22;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/22/17.
 */
public class OneHundredNinetyOneToZeroTest {

    OneHundredNinetyOneToZero NOTZ=null;
    @Before
    public void setup(){
        NOTZ=new OneHundredNinetyOneToZero();
    }
    @Test
    public void hammingWeight() throws Exception {
        System.out.println(NOTZ.hammingWeight(-11));
        System.out.println(Integer.bitCount(-11));
    }

}