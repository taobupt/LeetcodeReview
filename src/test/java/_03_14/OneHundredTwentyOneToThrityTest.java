package _03_14;

import _03_13.OneHundredTenToOneHundredTwenty;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/14/17.
 */
public class OneHundredTwentyOneToThrityTest {
    OneHundredTwentyOneToThrity ott=null;

    @Before
    public void setup(){
        ott=new OneHundredTwentyOneToThrity();
    }

    @Test
    public void longestConsecutiveByUnionFind() throws Exception {
        int []nums={100,4,200,1,3,2};
        System.out.println(ott.longestConsecutiveByUnionFind(nums));
    }

}