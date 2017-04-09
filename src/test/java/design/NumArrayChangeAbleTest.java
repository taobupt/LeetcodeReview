package design;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 4/8/17.
 */
public class NumArrayChangeAbleTest {

    @Test
    public void testNumArray(){
        int[]nums={1,3,5};
        NumArrayChangeAble num=new NumArrayChangeAble(nums);
        System.out.println(num.sumRange(0,2));
        num.update(1,2);
        System.out.println(num.sumRange(0,2));

    }

}