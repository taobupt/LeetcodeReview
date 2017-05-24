package _04_20;

import org.junit.Before;
import org.junit.Test;

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

}