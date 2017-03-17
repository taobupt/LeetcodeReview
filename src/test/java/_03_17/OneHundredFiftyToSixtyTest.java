package _03_17;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/16/17.
 */
public class OneHundredFiftyToSixtyTest {

    OneHundredFiftyToSixty ofts=null;
    @Before
    public void setup(){
        ofts=new OneHundredFiftyToSixty();
    }

    @Test
    public void reverseWords() throws Exception {
        System.out.println(ofts.reverseWords("   "));
        System.out.println(ofts.reverseWordsWithoutSplit(" "));
    }

}