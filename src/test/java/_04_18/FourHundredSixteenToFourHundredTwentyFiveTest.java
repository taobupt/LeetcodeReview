package _04_18;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by tao on 5/22/17.
 */
public class FourHundredSixteenToFourHundredTwentyFiveTest {
    
    FourHundredSixteenToFourHundredTwentyFive fhstfhtf=null;
    
    @Before
    public void setup(){
        fhstfhtf=new FourHundredSixteenToFourHundredTwentyFive();
    }
    
    @Test
    public void testPacific(){
        //int [][]matrix={{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        int [][]matrix={{1,1},{1,1}};
        fhstfhtf.pacificAtlantic(matrix);
    }

    @Test
    public void testPartitionSum(){
        int []nums={2,2,3,5};
        System.out.println(fhstfhtf.canPartition(nums));
    }
    @Test
    public void testReconstruct(){
        System.out.println(fhstfhtf.originalDigits("owoztneoer"));
    }

    @Test
    public void testWordSquare(){
        String[]args={"ball","asee","let","lep"};
        System.out.println(fhstfhtf.validWordSquare(Arrays.asList(args)));
    }

    @Test
    public void testWord(){
        String[]args={"aba","bab","ata","ata"};
        fhstfhtf.wordSquares(args);
    }

}