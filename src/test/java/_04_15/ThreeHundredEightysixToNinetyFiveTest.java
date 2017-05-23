package _04_15;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tao on 5/14/17.
 */
public class ThreeHundredEightysixToNinetyFiveTest {


    ThreeHundredEightysixToNinetyFive thestnf = null;

    @Before
    public void setup(){
        thestnf = new ThreeHundredEightysixToNinetyFive();
    }
    @Test
    public void lexicalOrder() throws Exception {
        List<Integer> res =thestnf.lexicalOrder(13);
        for(int num:res)
            System.out.println(num);
    }

    @Test
    public void longestPathFile()throws Exception{
        //"dir\n    file.txt"
        System.out.println(thestnf.lengthLongestPath("rzzmf\nv\n\tix\n\t\tiklav\n\t\t\ttqse\n\t\t\t\ttppzf\n\t\t\t\t\tzav\n\t\t\t\t\t\tkktei\n\t\t\t\t\t\t\thhmav\n\t\t\t"));
    }

    @Test
    public void testDecodeString(){
        System.out.println(thestnf.decodeString("2[abc]3[cd]ef"));
    }

    @Test
    public void testLongestRepat(){
        String ss="aa";
        System.out.println(thestnf.longestSubstring(ss,301));
    }

    @Test
    public void testOrder(){
        System.out.println( thestnf.lastRemaining(9));
    }

    @Test
    public void testRect(){
        int [][]rectangles={{0,0,1,1},
                {0,1,3,2},
                {1,0,2,2}};
        System.out.println(thestnf.isRectangleCover(rectangles));
    }
}