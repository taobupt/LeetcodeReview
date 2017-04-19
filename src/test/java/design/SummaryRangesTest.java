package design;

import common.Interval;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tao on 4/18/17.
 */
public class SummaryRangesTest {

    @Test
    public void testSummaryRange()throws Exception{
        SummaryRanges sr=new SummaryRanges();
        sr.addNum(1);
        List<Interval> res=sr.getIntervals();
        sr.addNum(3);
        res=sr.getIntervals();
        sr.addNum(7);
        res=sr.getIntervals();
        sr.addNum(2);
        res=sr.getIntervals();
        sr.addNum(6);
        res=sr.getIntervals();
    }

}