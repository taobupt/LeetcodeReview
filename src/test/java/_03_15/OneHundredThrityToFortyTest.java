package _03_15;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/14/17.
 */
public class OneHundredThrityToFortyTest {

    OneHundredThrityToForty ottf=null;

    @Before
    public void setup(){
        ottf=new OneHundredThrityToForty();
    }
    @Test
    public void partitionDP() throws Exception {
        ottf.partitionDP("abbab");
    }

}