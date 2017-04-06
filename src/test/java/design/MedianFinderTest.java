package design;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 4/5/17.
 */
public class MedianFinderTest {

    @Test
    public void testMedain(){
        MedianFinder mf=new MedianFinder();
        mf.addNum(-1);
        System.out.println(mf.findMedian());
        mf.addNum(-2);
        System.out.println(mf.findMedian());
        mf.addNum(-3);
        System.out.println(mf.findMedian());
        mf.addNum(-4);
        System.out.println(mf.findMedian());
        mf.addNum(-5);
        System.out.println(mf.findMedian());
    }

}