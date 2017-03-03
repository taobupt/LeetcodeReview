package _03_01;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Tao on 3/1/2017.
 */
public class ElevenToTwentyTest {

    public ElevenToTwenty et=null;

    @Before
    public void setup(){
        et=new ElevenToTwenty();
    }
    @Test
    public void isValidWithoutStacktest() throws Exception {
        System.out.println(et.isValidWithoutStack("(([]))"));
        String []strs={"abcz","abcdef"};
        Arrays.sort(strs);
        for(String str:strs)
            System.out.println(str);
    }

    @Test
    public void testThreeSumclosest()throws  Exception{
        int []nums={1,2,4,8,16,32,64,128};
        et.threeSumClosest(nums,82);
    }

    @Test
    public void testFoursum()throws  Exception{
        int []nums={2,1,0,-1};
        et.fourSum(nums,2);
    }

    @Test
    public void testPrefix()throws Exception{
        String[]strs={"ab","abc","abcd"};
        System.out.println(et.longestCommonPrefixByTrie(strs));
    }

}