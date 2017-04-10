package common;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 4/9/17.
 */
public class SortAlgoTest {

    SortAlgo sa=null;

    @Before
    public void setup(){
        sa=new SortAlgo();
    }
    @Test
    public void testMergeSort(){
        int[]nums={1,3,2,3,1};
        sa.mergeSort(nums);
        for(int x:nums)
            System.out.println(x);
    }

}