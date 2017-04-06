package design;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 4/6/17.
 */
public class NumArrayTest {

    @Test
    public void TestSumRange(){
        int[]nums={-2, 0, 3, -5, 2, -1};
        NumArray na=new NumArray(nums);
        System.out.println(na.sumRange(0,2));

    }

}