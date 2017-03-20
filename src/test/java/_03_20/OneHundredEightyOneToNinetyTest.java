package _03_20;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/20/17.
 */
public class OneHundredEightyOneToNinetyTest {

    public OneHundredEightyOneToNinety OEOT=null;
    @Before
    public void setup(){
        OEOT=new OneHundredEightyOneToNinety();
    }
    @Test
    public void reverseBits() throws Exception {
        System.out.println(OEOT.reverseBitsConcise(1));
    }

    @Test
    public void testReverseWords()throws Exception{
        String s="hi!";
        char []ss=s.toCharArray();
        OEOT.reverseWords(ss);
    }
    @Test
    public void testRotate()throws Exception{
        int []nums={1,2,3,4,5,6,7};
        OEOT.rotateGCD(nums,3);
        for(int x:nums)
            System.out.println(x);
    }


}