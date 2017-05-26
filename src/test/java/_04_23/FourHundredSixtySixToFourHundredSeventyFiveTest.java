package _04_23;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 5/25/17.
 */
public class FourHundredSixtySixToFourHundredSeventyFiveTest {


    FourHundredSixtySixToFourHundredSeventyFive FHSSTFHS =null;

    @Before
    public void setup(){
        FHSSTFHS = new FourHundredSixtySixToFourHundredSeventyFive();
    }

    @Test
    public void findSubstringInWraproundString() throws Exception {

    }

    @Test
    public void testOnesAndZeros(){
        String []args ={"10","0001","111001","1","0"};
        System.out.println(FHSSTFHS.findMaxForm(args,5,3));
    }

    @Test
    public void testRadius()throws Exception{
        int []houses ={282475249,622650073,984943658,144108930,470211272,101027544,457850878,458777923};
        int []heaters ={823564440,115438165,784484492,74243042,114807987,137522503,441282327,16531729,823378840,143542612};
        System.out.println(FHSSTFHS.findRadius(houses,heaters));
    }

}