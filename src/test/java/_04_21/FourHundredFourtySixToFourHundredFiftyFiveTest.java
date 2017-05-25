package _04_21;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 5/24/17.
 */
public class FourHundredFourtySixToFourHundredFiftyFiveTest {


    FourHundredFourtySixToFourHundredFiftyFive fhfstfhff=null;

    @Before
    public void setup(){
        fhfstfhff=new FourHundredFourtySixToFourHundredFiftyFive();
    }

    @Test
    public void findDisappearedNumbers() throws Exception {
        int []nums={4,3,2,7,8,2,3,1};
        fhfstfhff.findDisappearedNumbers(nums);
    }

}