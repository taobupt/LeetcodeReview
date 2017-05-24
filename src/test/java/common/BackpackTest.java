package common;

import org.junit.Before;
import org.junit.Test;
import sun.reflect.annotation.ExceptionProxy;

import static org.junit.Assert.*;

/**
 * Created by tao on 5/23/17.
 */
public class BackpackTest {


    Backpack backpack =null;

    @Before
    public void setup(){
        backpack=new Backpack();
    }
    @Test
    public void backPackUnoptimized() throws Exception {
        int []nums ={3,4,8,5};
        System.out.println(backpack.backPackUnoptimized(10,nums));
    }
    @Test
    public void backPackIII()throws Exception{
        int []nums={2,3,5,7};
        int []values={1,5,2,4};
        System.out.println(backpack.backPackIII(10,nums,values));
    }

    @Test
    public void testBackpackIV()throws Exception{
        int []nums={2,3,6,7};
        System.out.println(backpack.backPackIVSaveSpace(7,nums));
    }

    @Test
    public void testBackpackV()throws Exception{
        int []nums={1,2,3,3,7};
        System.out.println(backpack.backPackV(7,nums));
    }

}