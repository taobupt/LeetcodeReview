package _04_11;

import _04_10.ThreeHundredThirtysixToFourtyFive;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 4/18/17.
 */
public class ThreeHundredFourtysixToFiftyFiveTest {

    ThreeHundredFourtysixToFiftyFive ThTSTFF=null;

    @Before
    public void setup(){
        ThTSTFF=new ThreeHundredFourtysixToFiftyFive();
    }
    @Test
    public void topKFrequent() throws Exception {
        int[]nums={1,1,1,2,2,3,3,4,5,6,7,8,9,12,3,3,4,5,67,3,2,1,3,4,5,6};
        ThTSTFF.topKFrequent(nums,4);
    }

    @Test
    public void envelops()throws Exception{
        int [][]envelopes={{5,4},{6,4},{6,7},{2,3}};
        System.out.println(ThTSTFF.maxEnvelopes(envelopes));
    }

}