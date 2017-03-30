package _03_29;

import common.Interval;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/29/17.
 */
public class TwoHundredFortytySixToFiftyFiveTest {

    TwoHundredFortytySixToFiftyFive TFSTFF=null;
    @Before
    public void setup(){
        TFSTFF=new TwoHundredFortytySixToFiftyFive();
    }
    @Test
    public void isStrobogrammatic() throws Exception {
        System.out.println(TFSTFF.isStrobogrammatic("69"));
    }

    @Test
    public void canMeet(){

        Interval []intervals=new Interval[3];
        intervals[0]=new Interval(0,30);
        intervals[1]=new Interval(5,10);
        intervals[2]=new Interval(15,20);
        System.out.println(TFSTFF.minMeetingRooms(intervals));
    }

}