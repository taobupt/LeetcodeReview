package _03_05;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/5/17.
 */
public class FiftyOneToSixtyTest {
    FiftyOneToSixty ft=null;

    @Before
    public void setup(){
        ft=new FiftyOneToSixty();
    }

    @Test
    public void solveNQueens() throws Exception {
        ft.solveNQueens(4);
    }

}