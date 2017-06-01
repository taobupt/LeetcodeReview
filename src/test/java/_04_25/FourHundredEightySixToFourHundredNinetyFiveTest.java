package _04_25;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 5/27/17.
 */
public class FourHundredEightySixToFourHundredNinetyFiveTest {

    FourHundredEightySixToFourHundredNinetyFive fhestfhnf=null;

    @Before
    public void setup(){
        fhestfhnf =  new FourHundredEightySixToFourHundredNinetyFive();
    }

    @Test
    public void findMaxConsecutiveOnes() throws Exception {
        int []nums= {1,0,1,1,0,1};
        System.out.println(fhestfhnf.findMaxConsecutiveOnes(nums));
    }
    @Test
    public void testIncreasing(){
        int []nums = {4,6,7,7};
        fhestfhnf.findSubsequences(nums);
    }

    @Test
    public void testInterval(){
        int []timeserials = {1,4};
        System.out.println(fhestfhnf.findPoisonedDuration(timeserials,2));
    }

    @Test
    public void testMergesort(){
        int []nums ={3,2,1,0,-1,-2};
        System.out.println(fhestfhnf.reversePairs(nums));
        System.out.println("-----");
        fhestfhnf.merge(nums,0,nums.length-1);
        for(int x:nums)
            System.out.println(x);
        System.out.println(fhestfhnf.reverseNum);

    }

    @Test
    public void testHasPath(){
        int [][]maze ={{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
        int []start={0,4};
        int []dest={4,1};
        System.out.println(fhestfhnf.hasPath(maze,start,dest));
    }

}