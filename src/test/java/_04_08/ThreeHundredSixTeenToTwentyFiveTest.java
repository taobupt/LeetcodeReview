package _04_08;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tao on 4/10/17.
 */
public class ThreeHundredSixTeenToTwentyFiveTest {


    ThreeHundredSixTeenToTwentyFive THSTTTF=null;

    @Before
    public void setup(){
        THSTTTF =new ThreeHundredSixTeenToTwentyFive();
    }

    @Test
    public void maxSubArrayLen() throws Exception {
        int[]nums={1,0,-1};
        System.out.println(THSTTTF.maxSubArrayLen(nums,-1));
    }

    @Test
    public void coinChange()throws Exception{
        int[]nums={2};
        System.out.println(THSTTTF.coinChange(nums,3));
    }
    @Test
    public void testWiggleSort()throws Exception{
        int[]nums={1,4,1,5,1};
        THSTTTF.wiggleSort(nums);
    }

    @Test
    public void testRemoveDuplicate()throws Exception{
        System.out.println(THSTTTF.removeDuplicateLetters("bbcaac"));
    }

    @Test
    public void testGeneralizedAbbreviation()throws Exception{
        List<String> res=THSTTTF.generateAbbreviations("word");
        for(String str:res)
            System.out.println(str);
    }

}