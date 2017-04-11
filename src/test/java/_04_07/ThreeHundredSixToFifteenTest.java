package _04_07;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tao on 4/8/17.
 */
public class ThreeHundredSixToFifteenTest {


    ThreeHundredSixToFifteen THSTF=null;
    @Before
    public void setup(){
        THSTF=new ThreeHundredSixToFifteen();
    }

    @Test
    public void testCountSmaller(){
        int[]nums={5,2,6,1};
        THSTF.countSmaller(nums);
    }

    @Test
    public void testMergesort(){
        int []nums={4,5,6,7,3,2,1};
        List<Integer>res=THSTF.countSmallerMergeSort(nums);
        for(int x:res)
            System.out.println(x);
    }

    @Test
    public void testAdditive(){
        System.out.println(THSTF.isAdditiveNumber("101"));
    }

    @Test
    public void testFindMinimum(){

        int [][]edges={{0,1}};
        List<Integer>res=THSTF.findMinHeightTrees(2,edges);
        for(int x:res)
            System.out.println(x);
    }

}