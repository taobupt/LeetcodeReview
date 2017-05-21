package unsolved;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/29/17.
 */
public class UnsolvedQuestionTest {

    UnsolvedQuestion uq=null;
    @Before
    public void setup(){
        uq=new UnsolvedQuestion();
    }
    @Test
    public void wordsTyping() throws Exception {
        String[]word={"abc","de","f"};
        System.out.println(uq.wordsTypingBetter(word,4,6));
    }

    @Test
    public void testRemoveKdigits(){
        System.out.println(uq.removeKdigits("112",1));
    }

    @Test
    public void isRect(){
        int[]p1={0,0};
        int[]p2={1,1};
        int[]p3={1,0};
        int[]p4={0,1};
        System.out.println(uq.validSquare(p1,p2,p3,p4));
    }

    @Test
    public void testUnsorted(){
        int []nums ={1,3,2,2,2};
        System.out.println(uq.findUnsortedSubarray(nums));
    }


}