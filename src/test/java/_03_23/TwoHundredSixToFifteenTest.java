package _03_23;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/23/17.
 */
public class TwoHundredSixToFifteenTest {

    TwoHundredSixToFifteen TSTF=null;
    @Before
    public void setup(){
        TSTF=new TwoHundredSixToFifteen();
    }
    @Test
    public void canFinish() throws Exception {
        int [][]pre={{0,1}};
        //System.out.println(TSTF.canFinish(2,pre));
        //System.out.println(TSTF.findOrder(2,pre));
        int []nums={7,6,5,4,3,1,2,3,4,5,6,7,8,-100,-20,30,100};
        for(int i=1;i<=nums.length;++i)
            System.out.println(TSTF.findKthSmallest(nums,i));
//        TSTF.quickSort(nums);
//        for(int x:nums)
//            System.out.println(x);
    }

}