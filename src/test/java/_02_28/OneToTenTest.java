package _02_28;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tao on 2/28/2017.
 */
public class OneToTenTest {
    OneToTen oneToTen=null;

    @Before
    public void setup(){
        oneToTen=new OneToTen();
    }
    @Test
    public void convert() throws Exception {
        String s="PAYPALISHIRING";
        System.out.println(oneToTen.convert(s,3));
    }

    @Test
    public void findMedian()throws Exception{
        int []nums1={1,3};
        int []nums2={2};
        for(int i=1;i<=nums1.length+ nums2.length;++i)
            System.out.println(oneToTen.findMedian(nums1,0,nums2,0,i));
        //System.out.println(oneToTen.findMedianSortedArraysBetterWays(nums1,nums2));
    }

}