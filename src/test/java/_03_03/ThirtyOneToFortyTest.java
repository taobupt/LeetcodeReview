package _03_03;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by taobupt on 3/3/2017.
 */
public class ThirtyOneToFortyTest {

    public ThirtyOneToForty tf=null;

    @Before
    public void setup(){
        tf=new ThirtyOneToForty();
    }

    @Test
    public void upperBound() throws Exception {
        int []nums={1,2,3,4,5};
        assertEquals(tf.searchInsert(nums,6),tf.serachIntRecursive(nums,6));
        System.out.println(tf.upperBound(nums,0));
    }
    @Test
    public void testCountAndSay()throws Exception{
        System.out.println(tf.countAndSay(5));
    }

}