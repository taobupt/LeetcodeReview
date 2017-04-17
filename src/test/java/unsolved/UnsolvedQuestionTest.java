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


}