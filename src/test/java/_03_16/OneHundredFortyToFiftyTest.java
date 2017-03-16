package _03_16;

import common.Point;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/16/17.
 */
public class OneHundredFortyToFiftyTest {

    OneHundredFortyToFifty oftf=null;
    @Before
    public void setup(){
        oftf=new OneHundredFortyToFifty();
    }
    @Test
    public void evalRPNRecursive() throws Exception {
        String[]strs={"4", "13", "5", "/", "+"};
        System.out.println(oftf.evalRPNRecursive(strs));
    }
    @Test
    public void testPoints()throws Exception{
        Point[]points=new Point[4];
        points[0]=new Point(1,2);
        points[1]=new Point(3,6);
        points[2]=new Point(0,0);
        points[3]=new Point(1,3);
        System.out.println(oftf.maxPointsGCD(points));

    }

}