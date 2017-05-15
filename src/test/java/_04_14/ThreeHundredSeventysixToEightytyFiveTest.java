package _04_14;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 5/13/17.
 */
public class ThreeHundredSeventysixToEightytyFiveTest {
    public ThreeHundredSeventysixToEightytyFive thsstef=null;
    @Before
    public void setup(){
        thsstef=new ThreeHundredSeventysixToEightytyFive();
    }
    @Test
    public void deserialize() throws Exception {
        thsstef.deserializeIterative("[-123,[456,[789]],[123,124],245]");
    }

}