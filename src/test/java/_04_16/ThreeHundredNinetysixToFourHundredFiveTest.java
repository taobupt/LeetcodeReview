package _04_16;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tao on 5/20/17.
 */
public class ThreeHundredNinetysixToFourHundredFiveTest {


    ThreeHundredNinetysixToFourHundredFive thnstfh = null;

    @Before
    public void setup(){
        thnstfh = new ThreeHundredNinetysixToFourHundredFive();
    }

    @Test
    public void toHex() throws Exception {
//        for(int i=Integer.MIN_VALUE;i<=Integer.MAX_VALUE;++i){
//            //System.out.println(i);
//            assertEquals(thnstfh.toBinary(i),thnstfh.toBinaryConcise(i));
//        }

        List<List<Integer>>res= thnstfh.combinations(4,2);
        for(List<Integer>li:res) {
            for (int x : li)
                System.out.print(x);
            System.out.println();
        }

    }

    @Test
    public void testFindNthDigit(){
        System.out.println(thnstfh.findNthDigitConcise(189));
    }

}