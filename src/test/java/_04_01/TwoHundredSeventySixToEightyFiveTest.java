package _04_01;

import design.ZigzagIterator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tao on 4/2/17.
 */
public class TwoHundredSeventySixToEightyFiveTest {


    TwoHundredSeventySixToEightyFive EF=null;

    @Before
    public void setup(){
        EF=new TwoHundredSeventySixToEightyFive();
    }
    @Test
    public void numSquares() throws Exception {
        System.out.println(EF.numSquares(6550));
        List<Integer>res=new ArrayList<>();
        res.add(1);
        res.add(2);
        List<Integer>res1=new ArrayList<>();
        res1.add(3);
        res1.add(4);
        res1.add(5);
        res1.add(6);
        ZigzagIterator zigzag=new ZigzagIterator(res,res1);


    }

}