package _04_17;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 5/21/17.
 */
public class FourHundredsixToFourHundredFifteenTest {

    FourHundredsixToFourHundredFifteen fhstfhf=null;

    @Before
    public void setup(){
        fhstfhf=new FourHundredsixToFourHundredFifteen();
    }

    @Test
    public void validWordAbbreviation() throws Exception {
        System.out.println(fhstfhf.validWordAbbreviation("appee","a2e"));
    }

    @Test
    public void testConstructHeight(){
       int[][]people= {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
       fhstfhf.reconstructQueue(people);
    }

}