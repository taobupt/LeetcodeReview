package _04_20;

import common.Interval;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tao on 5/23/17.
 */
public class FourHundredThirtySixToFourHundredFortyFiveTest {

    FourHundredThirtySixToFourHundredFortyFive fhtstfhff=null;

    @Before
    public void setup(){
        fhtstfhff=new FourHundredThirtySixToFourHundredFortyFive();
    }

    @Test
    public void findAnagramsSaveTime() throws Exception {
        String s ="cbaebabacd",p="abc";
        fhtstfhff.findAnagramsSaveTime(s,p);
    }

    @Test
    public void testParser()throws Exception{

        System.out.println(fhtstfhff.parseTernaryByStack("T?T?F:5:3"));
    }

    @Test
    public void testInterval()throws Exception{
        int [][]nums={{3,4}, {2,3}, {1,2}};
        Interval []intervals=new Interval[3];
        for(int i=0;i<3;++i){
            intervals[i]=new Interval(nums[i][0],nums[i][1]);
        }

        fhtstfhff.findRightInterval(intervals);
    }

    @Test
    public void testArrangeCoins()throws Exception{
        System.out.println(fhtstfhff.arrangeCoinsConcise(10));
    }

    @Test
    public void testReconstruction()throws Exception{
        int []nums={5,3,2,4,1};
        int [][]numss={{5,3,2,4},{4,1},{1},{3},{2,4}};
        List<List<Integer>>res=new ArrayList<>();
        for(int i=0;i<numss.length;++i){
            List<Integer>tmp=new ArrayList<>();
            for(int x:numss[i])
                tmp.add(x);
            res.add(new ArrayList<>(tmp));
        }
        System.out.println(fhtstfhff.sequenceReconstruction(nums,res));
    }

}