package _03_04;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/4/17.
 */
public class FortyOneToFiftyTest {
    FortyOneToFifty ff=null;

    @Before
    public void setup(){
        ff=new FortyOneToFifty();
    }
    @Test
    public void firstMissingPositive() throws Exception {
        int[]nums={1};
        System.out.println(ff.firstMissingPositive(nums));
    }

    @Test
    public void testMutliply()throws Exception{
        System.out.println(ff.multiply("123456","123456"));
    }

    @Test
    public void testPermuation()throws Exception{
        int []nums={1,2,3};
        ff.permute(nums);
    }

}